package br.com.souza.paymentshistoriclambda;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.json.JSONObject;


public class PaymentsHistoricFunction implements RequestHandler<SQSEvent, String> {

    @Override
    public String handleRequest(SQSEvent event, Context context) {

        Regions clientRegion = Regions.fromName(System.getenv("REGION"));

        BasicAWSCredentials credentials = new BasicAWSCredentials(
                System.getenv("ACCESS_KEY"),
                System.getenv("SECRET_ACCESS_KEY"));

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        String bucketName = System.getenv("BUCKET_NAME");

        for (SQSEvent.SQSMessage msg : event.getRecords()) {
            System.out.println("Received message: " + msg.getBody());
            JSONObject jsonObject = new JSONObject(msg.getBody());

            JSONObject paymentObject = new JSONObject(jsonObject.getString("Message"));
            String orderId = paymentObject.getString("orderId");

            File file = new File("/tmp/" + orderId + "-payment.json");
            try (OutputStream os = new FileOutputStream(file)) {
                os.write(paymentObject.toString().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            s3Client.putObject(new PutObjectRequest(bucketName, orderId + "-payment.json", file));
            System.out.println("Successfully saved into Bucket S3");
            file.delete();
        }
        return "Successfully saved into Bucket S3";
    }

}
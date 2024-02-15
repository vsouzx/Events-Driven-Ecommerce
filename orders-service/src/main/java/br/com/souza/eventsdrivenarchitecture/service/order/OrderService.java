package br.com.souza.eventsdrivenarchitecture.service.order;

import br.com.souza.eventsdrivenarchitecture.database.model.Order;
import br.com.souza.eventsdrivenarchitecture.database.model.Product;
import br.com.souza.eventsdrivenarchitecture.database.repository.IOrderRepository;
import br.com.souza.eventsdrivenarchitecture.database.repository.IProductRepository;
import br.com.souza.eventsdrivenarchitecture.dto.OrderRequest;
import br.com.souza.eventsdrivenarchitecture.dto.OrderResponse;
import br.com.souza.eventsdrivenarchitecture.exceptions.InsufficientStockException;
import br.com.souza.eventsdrivenarchitecture.exceptions.OrderNotFoundException;
import br.com.souza.eventsdrivenarchitecture.service.sns.AwsSnsService;
import br.com.souza.eventsdrivenarchitecture.service.product.ProductService;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final IOrderRepository iOrderRepository;
    private final ProductService productService;
    private final IProductRepository iProductRepository;
    private final AwsSnsService awsSnsService;

    public OrderService(IOrderRepository iOrderRepository,
                        ProductService productService,
                        IProductRepository iProductRepository,
                        AwsSnsService awsSnsService) {
        this.iOrderRepository = iOrderRepository;
        this.productService = productService;
        this.iProductRepository = iProductRepository;
        this.awsSnsService = awsSnsService;
    }

    public void saveNewOrder(OrderRequest request) throws Exception {
        Product product = productService.getProductById(request.getProductId());

        if(product.getQuantity() < request.getQuantity()) throw new InsufficientStockException();

        product.setQuantity(product.getQuantity() - request.getQuantity());
        productService.updateProduct(product);

        Order order = new Order(request, product);
        iOrderRepository.save(order);

        awsSnsService.publish(new OrderResponse(order, iProductRepository).toString());
    }

    public OrderResponse getOrderById(UUID id) throws Exception{
        Order order = iOrderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        return new OrderResponse(order, iProductRepository);
    }

    public List<OrderResponse> getAllOrders() {
        return iOrderRepository.findAll().stream()
                .map(o -> new OrderResponse(o, iProductRepository))
                .sorted(Comparator.comparing(OrderResponse::getOrderTime).reversed())
                .toList();
    }

    public void updatePaymentStatus(UUID id, String status) throws Exception{
        Order order = iOrderRepository.findById(id)
                .orElseThrow(OrderNotFoundException::new);

        order.setPaymentStatus(status);

        iOrderRepository.save(order);
    }

}

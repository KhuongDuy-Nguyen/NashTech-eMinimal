package com.eminimal.backend.services.impl;

import com.eminimal.backend.models.Cart;
import com.eminimal.backend.models.Category;
import com.eminimal.backend.models.Product;
import com.eminimal.backend.models.Users;
import com.eminimal.backend.repository.CartRepository;
import com.eminimal.backend.repository.ProductRepository;
import com.eminimal.backend.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImplTest.class);

    @Mock
    UsersRepository usersRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductServiceImpl productService;

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    CartServiceImpl cartService;

    Product initProduct;
    Users initUsers;
    Cart initCart;
    Cart expectedCart;

    private final List<Cart> mockCart = new ArrayList<>();

    @BeforeEach
    void setup(){
        initProduct = mock(Product.class);
        initUsers = mock(Users.class);
        initCart = mock(Cart.class);
        expectedCart = mock(Cart.class);

        initCart = new Cart("1", 1, 15f, false, new ArrayList<>(), new Users());
        initUsers = new Users("1","name", "123","email@gmail.com","123","address","country", true, "ADMIN" );
        initProduct = new Product("1","name", "desc", 15f, new Category("1", "name", "desc"));

        for(int i =0; i< 5; i++){
            mockCart.add(new Cart());
        }

    }

    @Test
    void findAll() throws ExecutionException, InterruptedException {
        when(cartRepository.findAll()).thenReturn(mockCart);

        List<Cart> cartList = cartService.findAll();
        assertEquals(cartList.size(), mockCart.size());
    }

    @Test
    void findCartByUserID() {

        when(cartRepository.findByCartUsers_UserId("1")).thenReturn(mockCart);

        List<Cart> result = cartService.findCartByUserID("1");
        assertEquals(result.size(), mockCart.size());

    }

    @Test
    void findByID() throws Exception {
        when(cartRepository.findByCartID("1")).thenReturn(initCart);

        Cart result = cartService.findByID("1");
        assertEquals(result, initCart);
    }

    @Test
    @Disabled
//    Can not mock new Cart in cartService.save
    void save() throws Exception {
        when(userService.findById("1")).thenReturn(initUsers);
        initCart.setCartUsers(initUsers);
        when(cartRepository.save(initCart)).thenReturn(expectedCart);

        Cart result = cartService.save("1");
        assertEquals(result, expectedCart);
    }

    @Test
    void findCartWhenStatusIsFalse() throws Exception {
        when(cartRepository.findByCartUsers_UserIdAndCartStatusIsFalse("1")).thenReturn(initCart);
        Cart result = cartService.findCartWhenStatusIsFalse("1");
        assertEquals(result,initCart);
    }

    @Test
    void addProductInCart() throws Exception {
        when(cartRepository.findByCartID("1")).thenReturn(initCart);
        when(productService.findById("1")).thenReturn(initProduct);
        initCart.getCartProducts().add(initProduct);
        when(cartRepository.save(initCart)).thenReturn(expectedCart);

        log.error("Product: " + initProduct.getProductCost());

        Cart result = cartService.addProductInCart(initProduct.getProductID(), "1");
        assertEquals(result,expectedCart);
    }

    @Test
    void deleteProductInCart() throws Exception {
        when(cartRepository.findByCartID("1")).thenReturn(initCart);
        when(productService.findById("1")).thenReturn(initProduct);
        initCart.getCartProducts().remove(initProduct);
        initCart.setPrice(initCart.getPrice() - initProduct.getProductCost());
        initCart.setCartQuantity(initCart.getCartQuantity() - 1);
        when(cartRepository.save(initCart)).thenReturn(expectedCart);

        Cart result = cartService.deleteProductInCart("1", "1");
        assertEquals(result,expectedCart);
    }

    @Test
    void deleteCartById() throws Exception {
        when(cartRepository.findByCartID("1")).thenReturn(initCart);
        cartService.deleteCartById("1");
        verify(cartRepository, times(1)).deleteById("1");
    }
}
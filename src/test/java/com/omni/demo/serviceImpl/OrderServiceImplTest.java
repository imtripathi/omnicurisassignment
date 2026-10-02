package com.omni.demo.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.omni.demo.entity.Item;
import com.omni.demo.entity.OrderDetails;
import com.omni.demo.exception.OrderException;
import com.omni.demo.model.OrderRequest;
import com.omni.demo.model.ResponseMessage;
import com.omni.demo.repository.ItemRepository;
import com.omni.demo.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

	@Mock
	private ItemRepository itemRepo;

	@Mock
	private OrderRepository orderRepo;

	@InjectMocks
	private OrderServiceImpl orderService;

	@Test
	void createOrderReducesStockAndCalculatesTotalAmount() throws Exception {
		Item item = item(1L, 100, 5);
		when(itemRepo.findById(1L)).thenReturn(Optional.of(item));

		ResponseMessage response = orderService.createOrder(Arrays.asList(orderRequest(1L, 2, "user@example.com")));

		assertEquals("Success", response.getSatus());
		assertEquals(3, item.getStock());
		verify(itemRepo).save(item);

		ArgumentCaptor<OrderDetails> orderCaptor = ArgumentCaptor.forClass(OrderDetails.class);
		verify(orderRepo).save(orderCaptor.capture());
		assertEquals(1L, orderCaptor.getValue().getItemid());
		assertEquals(2, orderCaptor.getValue().getQuantity());
		assertEquals(200, orderCaptor.getValue().getTotalamount());
	}

	@Test
	void createOrderRejectsInsufficientStockWithoutReducingStock() {
		Item item = item(1L, 100, 1);
		when(itemRepo.findById(1L)).thenReturn(Optional.of(item));

		assertThrows(OrderException.class, () -> orderService.createOrder(Arrays.asList(orderRequest(1L, 2, "user@example.com"))));

		assertEquals(1, item.getStock());
		verify(orderRepo, never()).save(any(OrderDetails.class));
		verify(itemRepo, never()).save(any(Item.class));
	}

	@Test
	void createOrderRejectsCombinedQuantityThatExceedsAvailableStock() {
		Item item = item(1L, 100, 3);
		when(itemRepo.findById(1L)).thenReturn(Optional.of(item));

		assertThrows(OrderException.class, () -> orderService.createOrder(Arrays.asList(
				orderRequest(1L, 2, "first@example.com"),
				orderRequest(1L, 2, "second@example.com"))));

		assertEquals(3, item.getStock());
		verify(orderRepo, never()).save(any(OrderDetails.class));
		verify(itemRepo, never()).save(any(Item.class));
	}

	private Item item(long itemId, int price, int stock) {
		Item item = new Item();
		item.setItemid(itemId);
		item.setPrice(price);
		item.setStock(stock);
		return item;
	}

	private OrderRequest orderRequest(long itemId, int quantity, String emailId) {
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setItemId(itemId);
		orderRequest.setQuantity(quantity);
		orderRequest.setEmailId(emailId);
		return orderRequest;
	}
}

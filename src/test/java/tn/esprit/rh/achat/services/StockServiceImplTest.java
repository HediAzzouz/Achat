import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StockServiceImplTest {

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllStocks() {
        List<Stock> expectedStocks = new ArrayList<>();
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setQte(17);
        stock.setQteMin(12);
        expectedStocks.add(stock);
        when(stockRepository.findAll()).thenReturn(expectedStocks);
        List<Stock> actualStocks = stockService.retrieveAllStocks();
        assertEquals(expectedStocks.size(), actualStocks.size());
    }

    @Test
    public void testAddStock() {
        Stock stock = new Stock();
        stock.setIdStock(1L);
        stock.setQte(17);
        stock.setQteMin(12);
        when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
        Stock addedStock = stockService.addStock(stock);
        assertEquals(stock, addedStock);
    }

    @Test
    public void testDeleteStock() {
        Long stockIdToDelete = 1L;
        stockService.deleteStock(stockIdToDelete);
        verify(stockRepository).deleteById(stockIdToDelete);
    }

     @Test
    public void testUpdateStock() {
         Stock updatedStock = new Stock();
         updatedStock.setIdStock(1L);
         updatedStock.setQte(17);
         updatedStock.setQteMin(12);
        when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(updatedStock);
        Stock result = stockService.updateStock(updatedStock);
        assertEquals(updatedStock, result);
    }

    @Test
    public void testRetrieveStock() {
        Long stockId = 1L;
        Stock expectedStock = new Stock();
        expectedStock.setIdStock(stockId);
        expectedStock.setQte(17);
        expectedStock.setQteMin(12);

        when(stockRepository.findById(stockId)).thenReturn(java.util.Optional.ofNullable(expectedStock));

        Stock actualStock = stockService.retrieveStock(stockId);

        assertEquals(expectedStock, actualStock);
    }
    @Test
    public void testRetrieveStatusStock() {
       final Stock stockEnRouge = new Stock();
        stockEnRouge.setIdStock(1L);
        stockEnRouge.setQte(17);
        stockEnRouge.setQteMin(19);
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stockEnRouge);
        when(stockRepository.retrieveStatusStock()).thenReturn(stocks);
        System.out.println(stockService.retrieveStatusStock());
        assertTrue(stockService.retrieveStatusStock().contains(": le stock null a une quantité de 17 inférieur "));
    }
}
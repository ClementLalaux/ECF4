package com.example.objetceleste;

import com.example.objetceleste.entity.ObjetCeleste;
import com.example.objetceleste.repository.ObjetCelesteRepository;
import com.example.objetceleste.service.ObjectCelesteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ObjetCelesteApplicationTests {

	@Mock
	private ObjetCelesteRepository objetCelesteRepository;

	@InjectMocks
	private ObjectCelesteService objectCelesteService;

	@Test
	public void shouldAddNewObjetCelesteWhenValidData(){
		ObjetCeleste objetCeleste = new ObjetCeleste("Terre",1892D,1499196616D);
		Mockito.when(objetCelesteRepository.save(objetCeleste)).thenReturn(objetCeleste);

		ObjetCeleste objetCeleste1 = objectCelesteService.createObjectCeleste("Terre",1892D,1499196616D);

		Assertions.assertEquals(objetCeleste,objetCeleste1);
	}



	@Test
	public void shouldReturnExceptionWhenObjectCelesteByIdNotFound(){
		ObjetCeleste objetCeleste = new ObjetCeleste(1L,"Terre",1892D,1499196616D);
		Mockito.when(objetCelesteRepository.save(objetCeleste)).thenReturn(objetCeleste);
		Long id = 100L;
		Assertions.assertThrowsExactly(RuntimeException.class,()->{
			objectCelesteService.getObjetCelesteById(id);
		});
	}

	@Test
	public void shouldReturnObjetCelesteByIdWhenFound(){
		ObjetCeleste objetCeleste = new ObjetCeleste(1L,"Terre",1892D,1499196616D);
		Optional<ObjetCeleste> objetCeleste1 = Optional.of(objetCeleste);
		Mockito.when(objetCelesteRepository.findById(1L)).thenReturn(objetCeleste1);
		Assertions.assertEquals(objetCeleste,objectCelesteService.getObjetCelesteById(1L));

	}

	@Test
	public void shouldReturnAllObjetsCelesteWhenFound(){
		List<ObjetCeleste> objetCelesteList = Arrays.asList(
				new ObjetCeleste(1L,"Terre",1892D,1499196616D),
				new ObjetCeleste(2L,"Terre",1892D,1499196616D),
				new ObjetCeleste(3L,"Terre",1892D,1499196616D)
		);
		List<ObjetCeleste> objetCelesteList1 = objetCelesteList;

		Mockito.when(objetCelesteRepository.findAll()).thenReturn(objetCelesteList1);

		Assertions.assertEquals(objetCelesteList,objetCelesteList1);
	}
}

package com.sophos.demodo.test;

import com.sophos.demodo.DemoDO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestDemoDO {

	@Test
	public void testSumar() {
		Assert.assertEquals(
			"Resultado errado",
			10,
			DemoDO.sumar(5,5)
		);

		Assert.assertEquals(
			"Resultado errado",
			54,
			DemoDO.sumar(45, 9)
		);

		Assert.assertEquals(
			"Resultado errado",
			5,
			DemoDO.sumar(5,0)
		);

		Assert.assertEquals(
			"Resultado errado",
			-25,
			DemoDO.sumar(-5,-20)
		);
	}

	@Test
	public void testRestar() {
		Assert.assertEquals(
			"Resultado errado",
			5,
			DemoDO.restar(10, 5)
		);

		Assert.assertEquals(
			"Resultado errado",
			-15,
			DemoDO.restar(-10, 5)
		);

		Assert.assertEquals(
			"Resultado errado",
			10,
			DemoDO.restar(10, 0)
		);
	}

	@Test
	public void testMultiplicar() {
		Assert.assertEquals(
			"Resultado errado",
			50,
			DemoDO.multiplicar(10, 5)
		);

		Assert.assertEquals(
			"Resultado errado",
			-50,
			DemoDO.multiplicar(-10, 5)
		);

		Assert.assertEquals(
			"Resultado errado",
			-50,
			DemoDO.multiplicar(10, -5)
		);

		Assert.assertEquals(
			"Resultado errado",
			10,
			DemoDO.multiplicar(10, 1)
		);

		Assert.assertEquals(
			"Resultado errado",
			0,
			DemoDO.multiplicar(10, 0)
		);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testDividir() {
		Assert.assertEquals(
			"Resultado errado",
			2,
			DemoDO.dividir(10, 5)
		);

		Assert.assertEquals(
			"Resultado errado",
			-2,
			DemoDO.dividir(-10, 5)
		);

		Assert.assertEquals(
			"Resultado errado",
			-2,
			DemoDO.dividir(10, -5)
		);

		Assert.assertEquals(
			"Resultado errado",
			0,
			DemoDO.dividir(0, 100)
		);

		Assert.assertEquals(
			"Resultado errado",
			10,
			DemoDO.dividir(10, 1)
		);

		expectedException.expect(ArithmeticException.class);
		expectedException.expectMessage("División por cero");
		Assert.assertEquals(
			"Resultado errado",
			0,
			DemoDO.dividir(10, 0)
		);
	}

	@Test
	public void testCalcular() {
		// Flujo normal
		Assert.assertThat(
			"Mensaje de salida no corresponde",
			DemoDO.calcular(81, 9),
			CoreMatchers.allOf(
				StringContains.containsString("(81 + 9) = 90"),
				StringContains.containsString("(81 - 9) = 72"),
				StringContains.containsString("(81 x 9) = 729"),
				StringContains.containsString("(81 / 9) = 9")
			)
		);

		// Flujo con división por cero
		expectedException.expect(ArithmeticException.class);
		expectedException.expectMessage("División por cero");
		Assert.assertEquals(
			"Mensaje de salida no corresponde",
			"",
			DemoDO.calcular(0, 0)
		);
	}

}

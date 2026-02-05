package com.hana8.hello;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CalcTest {

  private static Calc c1;
  private static Calc c2;

  @BeforeAll
  static void setup() {
    c1 = new Calc(1, 2);
    c2 = new Calc(3, 5);
  }

  @Test
  void add() {
    assertEquals(3, c1.add());
    assertEquals(8, c2.add());

    Assertions.assertThat(c1.add()).isEqualTo(3);
    Assertions.assertThat(c2.add()).isEqualTo(8);
  }

  @Test
  void sub() {
    assertEquals(1, c1.sub());
    assertEquals(2, c2.sub());

    assertThat(c1.sub()).isEqualTo(1);
    assertThat(c2.sub()).isEqualTo(2);
  }
}

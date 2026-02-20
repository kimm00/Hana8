package com.hana8.hello.annotations;

public @interface NotNull {

	String value() default "Need NotNull!";

}

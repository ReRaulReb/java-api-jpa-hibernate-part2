package com.booleanuk.api.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {


	private String first_name;

	private String last_name;

	private String email;

	private boolean alive;


}

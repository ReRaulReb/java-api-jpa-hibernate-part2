package com.booleanuk.api.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {


	private String title;

	private String genre;

	private Integer author_id;

	private Integer publisher_id;

}

package com.keduit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//모든 멤버를 생성자
@NoArgsConstructor//기본생성자
public class SampleVO {
	private Integer Mno;
	private String firstName;
	private String lastName;
}


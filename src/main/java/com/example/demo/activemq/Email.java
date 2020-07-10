package com.example.demo.activemq;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {
	private String to;
	private String body;
	private Long time;
}

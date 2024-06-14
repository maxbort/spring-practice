package com.poscodx.container.config.videosystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poscodx.container.videosystem.Avengers;
import com.poscodx.container.videosystem.DVDPlayer;
import com.poscodx.container.videosystem.DigitalVideoDisc;

@Configuration
public class DVDPlayerConfig {
	
	public void hello() {
		System.out.println("hello container");
	}
	
	@Bean
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}
	// 주입(Injection)하기 1
	// Bean 생성 메소드를 직접 호출하는 방법
	@Bean("dvdPlayer01") // Bean이름 명시 가능 명시 안하면 메소드 이름이 빈 이름이 됨.
	public DVDPlayer dvdPlayer1() {
		return new DVDPlayer(avengers());
	}
	// 주입(Injection)하기 2
	// Parameter로 bean을 전달하는 방법 가장 많이 하는 방법
	// 생성자 주입
	@Bean
	public DVDPlayer dvdPlayer2(DigitalVideoDisc dvd) {
		return new DVDPlayer(dvd);
	}
	// 주입(Injection)하기 2
	// Parameter로 bean을 전달하는 방법 가장 많이 하는 방법
	// setter 주입
	@Bean
	public DVDPlayer dvdPlayer3(DigitalVideoDisc dvd) {
		DVDPlayer dvdPlayer = new DVDPlayer();
		dvdPlayer.setDvd(dvd);
		
		return dvdPlayer;
	}

//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views");
//		return viewResolver;
//	}
}

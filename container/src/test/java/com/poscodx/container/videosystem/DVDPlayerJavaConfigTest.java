package com.poscodx.container.videosystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poscodx.container.config.videosystem.DVDPlayerConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {DVDPlayerConfig.class})
public class DVDPlayerJavaConfigTest {
	
	@Autowired
	// 같은 타입의 빈이 2개 이상 있는 경우
	// 설정 클래스의 빈 생성 메소드의 @Bean의 name(default) 속성으로 Qualifier 하기
	@Qualifier("dvdPlayer01")
	private DVDPlayer dvdPlayer01;
	
	@Autowired
	// 같은 타입의 빈이 2개 이상 있는 경우
	// 설정 클래스의 빈 생성 메소드 이름으로 Qualifier 하기
	@Qualifier("dvdPlayer2")
	private DVDPlayer dvdPlayer02;
	
	@Test
	public void testDvDPlayer01NotNull() {
		assertNotNull(dvdPlayer01);
	}
	
	@Test
	public void testPlay01() {
		assertEquals("Playing Movie Marvel's Avengers", dvdPlayer01.play());
	}
	
	@Test
	public void testDvDPlayer02NotNull() {
		assertNotNull(dvdPlayer02);
	}
	
	@Test
	public void testPlay02() {
		assertEquals("Playing Movie Marvel's Avengers", dvdPlayer02.play());
	}
}

/*ログイン・ログアウト認証・許可のルール
 * 
 */

package com.example.supplies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.supplies.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration //設定クラス
@EnableWebSecurity //Securityを有効化
@EnableMethodSecurity//method単位で権限制御
@RequiredArgsConstructor
public class SecurityConfig {
	private final UserDetailsServiceImpl userDetailsService;

	@Bean //戻り値をSpringに登録
	//リクエストがcontrollerに行く前に通る
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.csrf(csrf -> csrf.disable())  //フォーム送信のセキュリティ機能/OFF
			.authorizeHttpRequests(auth -> auth //URLごとにアクセスルールを書く
				.requestMatchers("/login", "/register").permitAll() //ログインしてなくても見れる
				.anyRequest().authenticated() //ログインいしてないと見れない
			)
			.formLogin(login -> login //フォーム認証(ログイン機能)
				.loginPage("/login") //ログイン画面のURL
				.defaultSuccessUrl("/menu", true) //ログイン成功したらitem画面遷移
				
			)
			.logout(logout -> logout //ログアウト処理の設定
				.logoutSuccessUrl("/login") //ログアウト後はログイン画面
			)
			.userDetailsService(userDetailsService);//ユーザー情報の取得クラス

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();//パスワードをBCryptでハッシュ化する//
	}
}

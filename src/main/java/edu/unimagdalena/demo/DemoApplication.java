package edu.unimagdalena.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		/*User user = new User(1, "Juan", 20);
		User user2 = new User(2, "Pedro", 30);
		User user3 = new User(3, "Maria", 40);
		List<User> usuarios = Arrays.asList(user, user2, user3);
		List<User> usuariosMayoresA20 = usuarios.stream()
		.filter(u -> u.getEdad() > 20)
		.collect(Collectors.toList());
		User user = null;*/
		//User user = new User(1, "Juan", 20);
		//Optional<User> userOptional = Optional.of(user);//La condición del of es que no puede ser nulo
		//Optional<User> userOptional = Optional.ofNullable(user);// Para tratar objetos nulos
		/*if(userOptional.isPresent()) {
			System.out.println("No es nulo");
			user = userOptional.get();//para obtener el objeto o el valor que se tiene allí
			System.out.println(user.getName());
		} else {
			System.out.println("Es null");
		}*/
		//userOptional.ifPresent(u -> System.out.println(u.getName()));//Si el objeto no es nulo, se ejecuta el código
		//user = userOptional.orElseGet(()-> new User(0, "No existe", 0));//Si el objeto es nulo, retorna un objeto por defecto
		//userOptional.orElseThrow((IllegalArgumentException::new));//Si el objeto es nulo, lanza una excepción si no lo es hara otra cosa, mismo resultado que el de abajo
		//userOptional.orElseThrow(()-> new IllegalArgumentException());//Si el objeto es nulo, lanza una excepción si no lo es hara otra cosa
		//userOptional.map(u -> u.getEdad())
		//				.filter(e -> e >= 21).isPresent();//De esa información que traigo y trabajo con get lo prceso con filter, me sirve pa actualizar también
	}

	


}

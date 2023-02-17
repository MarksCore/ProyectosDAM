import 'package:clicker/objetos/MejorasActivas.dart';
import 'MejorasPasivas.dart';

class Usuario {
  late int id;
  String username;
  String password;
  int dinero;
  late List<MejorasActivas> mejorasActivas;
  late List<MejorasPasivas> mejorasPasivas;
  int clicksRealizados;
  int menasConsumidas;
  int diamantesTotales;
  int menasSegundo;
  int menasClick;

  Usuario(
      this.id,
      this.username,
      this.password,
      this.dinero,
      this.mejorasActivas,
      this.mejorasPasivas,
      this.clicksRealizados,
      this.menasConsumidas,
      this.diamantesTotales,
      this.menasClick,
      this.menasSegundo);

  Usuario.p(
      this.username,
      this.password,
      this.dinero,
      this.clicksRealizados,
      this.menasConsumidas,
      this.diamantesTotales,
      this.menasClick,
      this.menasSegundo);

  // Función que retorna un mapa que tendrá los datos del usuario (convierte usuario a mapa).
  // Map utilizado como intermediario para registrar usuarios en la BD mediante registro.
  // El id se crea de manera autoincremental.
  Map<String, dynamic> toMapRegistro() {
    return {
      'username': username,
      'password': password,
      'dinero': dinero,
      'clicksRealizados': clicksRealizados,
      'menasConsumidas': menasConsumidas,
      'diamantesTotales': diamantesTotales,
      'menasClick': menasClick,
      'menasSegundo': menasSegundo
    };
  }

  // Map utilizado como intermediario para actualizar usuarios en la BD.
  // El id se utiliza para ubicar al usuario en la BD y actualizar datos del mismo.
  Map<String, dynamic> toMapReemplazo() {
    return {
      'id': id,
      'username': username,
      'password': password,
      'dinero': dinero,
      'clicksRealizados': clicksRealizados,
      'menasConsumidas': menasConsumidas,
      'diamantesTotales': diamantesTotales,
      'menasClick': menasClick,
      'menasSegundo': menasSegundo
    };
  }

  // Función que hace extrae los datos del mapa para convertir a usuario (convierte mapa a usuario).
  // Se llama siempre que queramos sacar usuario de la BD.
  Usuario.fromMap(Map<String, dynamic> result)
      : id = result["id"],
        username = result["username"],
        password = result["password"],
        dinero = result["dinero"],
        clicksRealizados = result["clicksRealizados"],
        menasConsumidas = result["menasConsumidas"],
        diamantesTotales = result["diamantesTotales"],
        menasClick = result["menasClick"],
        menasSegundo = result["menasSegundo"];
}

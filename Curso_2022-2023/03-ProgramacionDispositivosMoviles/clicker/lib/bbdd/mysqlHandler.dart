import 'package:clicker/objetos/MejorasActivas.dart';
import 'package:clicker/objetos/MejorasPasivas.dart';
import 'package:mysql1/mysql1.dart';
import 'package:clicker/objetos/Usuario.dart';

class MysqlHandler {
  //crear database lo hizo chatgpt, sabe dios si esta libreria lo permite (segun documentacion oficial se deberia acceder a db, pero no dicen que sea obligatorio ni que se pueda ni que no crear bbdd)
  //pero solo esta, crear tables segun la documentacion oficial puede
  //no probe nada ni llame nada en otro lado, solo hice funciones y a mano, si fallan cosas probablemente sean fallos al escribir
  //se que se pueden junntar funciones y asi tener menos pero de mas en un futuro podria ser util
  Future<void> createDatabase() async {
    final conn = await MySqlConnection.connect(
        ConnectionSettings(host: '10.0.2.2', port: 3306, user: 'root'));
    await conn.query('CREATE DATABASE IF NOT EXISTS prueba.db');
    await conn.close();
  }

  Future<void> crearTablas() async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    await conexion.query('CREATE TABLE IF NOT EXIST usuarios ('
        'id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,'
        'username VARCHAR(30),'
        'password VARCHAR(30),'
        'dinero INT,'
        'clicksRealizados INT,'
        'menasConsumidas INT,'
        'diamantesTotales INT,'
        'menasSegundo INT,'
        'menasClick INT)');

    await conexion.query('CREATE TABLE IF NOT EXIST mejorasActivas ('
        'id INTEGER NOT NULL AUTO_INCREMENT,'
        ' nombre TEXT,'
        ' coste INTEGER,'
        ' produccion INTEGER,'
        ' mP REAL,'
        ' mC REAL,'
        ' nivel INTEGER,'
        ' idUsuario INTEGER,'
        ' vecesUpgradeado INTEGER,'
        ' ruta TEXT,'
        ' comprado INTEGER,'
        ' PRIMARY KEY(id, idUsuario))');

    await conexion.query('CREATE TABLE IF NOT EXIST mejorasPasivas ('
        'id INTEGER NOT NULL AUTO_INCREMENT,'
        ' nombre TEXT,'
        ' coste INTEGER,'
        ' produccion INTEGER,'
        ' mP REAL,'
        ' mC REAL,'
        ' nivel INTEGER,'
        ' idUsuario INTEGER,'
        ' vecesUpgradeado INTEGER,'
        ' ruta TEXT,'
        ' comprado INTEGER,'
        ' PRIMARY KEY(id, idUsuario))');

    await conexion.close();
  }

  Future<void> insertarUsuario(Usuario usuario) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    await conexion.query(
        'INSERT INTO usuarios (username, password, dinero, clicksRealizados, menasConsumidas, diamantesTotales, menasClick, menasSegundo) value (?, ?, ?, ?, ?, ?, ?, ?)',
        [
          usuario.username,
          usuario.password,
          usuario.dinero,
          usuario.clicksRealizados,
          usuario.menasConsumidas,
          usuario.diamantesTotales,
          usuario.menasClick,
          usuario.menasSegundo
        ]);

    await conexion.close();
  }

  Future<void> actualizarUsuario(Usuario usuario) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    await conexion.query(
        'UPDATE usuarios SET username = ?, password = ?, dinero = ?, clicksRealizados ?, menasConsumidas = ?, diamantesTotales = ?, menasClick = ?, menasSegundo = ? WHERE id = ?',
        [
          usuario.username,
          usuario.password,
          usuario.dinero,
          usuario.clicksRealizados,
          usuario.menasConsumidas,
          usuario.diamantesTotales,
          usuario.menasClick,
          usuario.menasSegundo,
          usuario.id
        ]);

    await conexion.close();
  }

  Future<void> insertarMejoraActiva(MejorasActivas mejora) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    await conexion.query(
        'INSERT INTO mejorasActivas (idUsuario, id, nombre, coste, produccion, mP, mC, nivel, vecesUpgradeado, ruta) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',
        [
          mejora.idUsuario,
          mejora.id,
          mejora.nombre,
          mejora.coste,
          mejora.produccion,
          mejora.mP,
          mejora.mC,
          mejora.nivel,
          mejora.vecesUpgradeado,
          mejora.ruta
        ]);

    await conexion.close();
  }

  Future<void> insertarMejoraPasiva(MejorasPasivas mejora) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    await conexion.query(
        'INSERT INTO mejorasPasivas (idUsuario, id, nombre, coste, produccion, mP, mC, nivel, vecesUpgradeado, ruta) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)',
        [
          mejora.idUsuario,
          mejora.id,
          mejora.nombre,
          mejora.coste,
          mejora.produccion,
          mejora.mP,
          mejora.mC,
          mejora.nivel,
          mejora.vecesUpgradeado,
          mejora.ruta
        ]);

    await conexion.close();
  }

  Future<void> actualizarMejoraActiva(MejorasActivas mejora) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    await conexion.query(
        'UPDATE mejorasActivas SET nombre = ?, coste = ?, produccion = ?, mP = ?, mC = ?, nivel = ?, vecesUpgradeado = ?, ruta = ?) WHERE idUsuario = ? AND id = ?',
        [
          mejora.nombre,
          mejora.coste,
          mejora.produccion,
          mejora.mP,
          mejora.mC,
          mejora.nivel,
          mejora.vecesUpgradeado,
          mejora.ruta,
          mejora.idUsuario,
          mejora.id,
        ]);

    await conexion.close();
  }

  Future<void> actualizarMejoraPasiva(MejorasPasivas mejora) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    await conexion.query(
        'UPDATE mejorasPasivas SET nombre = ?, coste = ?, produccion = ?, mP = ?, mC = ?, nivel = ?, vecesUpgradeado = ?, ruta = ?) WHERE idUsuario = ? AND id = ?',
        [
          mejora.nombre,
          mejora.coste,
          mejora.produccion,
          mejora.mP,
          mejora.mC,
          mejora.nivel,
          mejora.vecesUpgradeado,
          mejora.ruta,
          mejora.idUsuario,
          mejora.id,
        ]);

    await conexion.close();
  }

  Future<bool> compMejoraPasiva(MejorasPasivas mejora) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    String sql = "SELECT * FROM mejorasPasivas WHERE idUsuario = '" +
        mejora.idUsuario.toString() +
        "' AND id = '" +
        mejora.id.toString() +
        "'";

    Results results = await conexion.query(sql);

    await conexion.close();

    return results.isNotEmpty;
  }

  Future<bool> compMejoraActiva(MejorasPasivas mejora) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    String sql = "SELECT * FROM mejorasActivas WHERE idUsuario = '" +
        mejora.idUsuario.toString() +
        "' AND id = '" +
        mejora.id.toString() +
        "'";

    Results results = await conexion.query(sql);

    await conexion.close();

    return results.isNotEmpty;
  }

  Future<bool> compUsuario(Usuario usuario) async {
    final conexion = await MySqlConnection.connect(ConnectionSettings(
        host: '10.0.2.2', port: 3306, user: 'root', db: 'prueba.db'));

    String sql =
        "SELECT * FROM usuarios WHERE id = '" + usuario.id.toString() + "'";

    Results results = await conexion.query(sql);

    await conexion.close();

    return results.isNotEmpty;
  }
}

import 'package:clicker/objetos/MejorasActivas.dart';
import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import '../objetos/MejorasPasivas.dart';
import '../objetos/Usuario.dart';

class DBHandler {
  // Esta función se llama en main para iniciar (o crear si no existe) la BD.
  // También se llama para realizar cualquier operación sobre los datos de la BD.
  Future<Database> initializedDB() async {
    String path = await getDatabasesPath();
    return openDatabase(
      join(path, 'prueba.db'),
      version: 1,
      onCreate: (Database db, int version) async {
        await db.execute(
            "CREATE TABLE usuarios(id INTEGER PRIMARY KEY, username TEXT, password TEXT, "
            "dinero INTEGER, clicksRealizados INTEGER, menasConsumidas INTEGER, diamantesTotales INTEGER, menasClick INTEGER, menasSegundo INTEGER)");
        await db.execute(
            "CREATE TABLE mejorasActivas(id INTEGER, nombre TEXT, coste INTEGER, "
            "produccion INTEGER, mP REAL, mC REAL, nivel INTEGER, idUsuario INTEGER, vecesUpgradeado INTEGER, ruta TEXT, comprado INTEGER, PRIMARY KEY(id, idUsuario))");
        await db.execute(
            "CREATE TABLE mejorasPasivas(id INTEGER, nombre TEXT, coste INTEGER, "
            "produccion INTEGER, mP REAL, mC REAL, nivel INTEGER, idUsuario INTEGER, vecesUpgradeado INTEGER, ruta TEXT, comprado INTEGER, PRIMARY KEY(id, idUsuario))");
      },
    );
  }

  // Función para añadir usuario a la BD.
  Future<void> insertUsuarioRegistro(Usuario usuario) async {
    final Database db = await initializedDB();

    await db.insert('usuarios', usuario.toMapRegistro(),
        conflictAlgorithm: ConflictAlgorithm.replace);
  }

  // Función para actualizar usuario de la BD (reemplaza usuario correspondiente a un id con datos nuevos manteniedo ese id).
  Future<void> insertUsuario(Usuario usuario) async {
    final Database db = await initializedDB();

    await db.insert('usuarios', usuario.toMapReemplazo(),
        conflictAlgorithm: ConflictAlgorithm.replace);
  }

  // Funciones para añadir y actualizar mejora.
  Future<void> insertMejoraActiva(MejorasActivas mejorasActiva) async {
    final Database db = await initializedDB();

    await db.insert('mejorasActivas', mejorasActiva.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
  }

  Future<void> insertMejoraPasiva(MejorasPasivas mejorasPasivas) async {
    final Database db = await initializedDB();

    await db.insert('mejorasPasivas', mejorasPasivas.toMap(),
        conflictAlgorithm: ConflictAlgorithm.replace);
  }

  // Funciones para extraer mejoras de usuario concreto en el momento de loguearse y cargarlas.
  Future<List<MejorasActivas>?> retrieveMejorasActivas(int? id) async {
    final Database db = await initializedDB();
    final List<Map<String, Object?>> queryResult = await db
        .query('mejorasActivas', where: "idUsuario = ?", whereArgs: [id]);
    return queryResult.map((e) => MejorasActivas.fromMap(e)).toList();
  }

  Future<List<MejorasPasivas>?> retrieveMejorasPasivas(int? id) async {
    final Database db = await initializedDB();
    final List<Map<String, Object?>> queryResult = await db
        .query('mejorasPasivas', where: "idUsuario = ?", whereArgs: [id]);
    return queryResult.map((e) => MejorasPasivas.fromMap(e)).toList();
  }

  // Función para extraer todos los usuarios de la BD con el fin de comprobar
  // que exista un usuario específico.
  Future<List<Usuario>?> retrieveUsuario() async {
    final Database db = await initializedDB();
    final List<Map<String, Object?>> queryResult = await db.query('usuarios');
    return queryResult.map((e) => Usuario.fromMap(e)).toList();
  }

  // Función para extraer usuario concreto para loguearse.
  Future<List<Usuario>?> retrieveUsuarioEspecifico(String username) async {
    final Database db = await initializedDB();
    final List<Map<String, Object?>> queryResult = await db
        .query('usuarios', where: 'username = ?', whereArgs: [username]);
    return queryResult.map((e) => Usuario.fromMap(e)).toList();
  }

  // Función que borra registro usuario de tabla usuario y las asociadas por id a las mejoras.
  Future<void> deleteUsuario(int id) async {
    final db = await initializedDB();
    await db.delete(
      'usuarios',
      where: "id = ?",
      whereArgs: [id],
    );
    await db.delete('mejorasPasivas', where: "idUsuario = ?", whereArgs: [id]);
    await db.delete('mejorasActivas', where: "idUsuario = ?", whereArgs: [id]);
  }

  Future<void> resetearPartida(Usuario usuario) async {
    final db = await initializedDB();
    usuario.dinero = 0;
    usuario.mejorasActivas = [];
    usuario.mejorasPasivas = [];
    usuario.clicksRealizados = 0;
    usuario.menasConsumidas = 0;
    usuario.diamantesTotales = 0;
    usuario.menasClick = 0;
    usuario.menasSegundo = 0;
    await insertUsuario(usuario);
    await db.delete('mejorasPasivas', where: "idUsuario = ?", whereArgs: [usuario.id]);
    await db.delete('mejorasActivas', where: "idUsuario = ?", whereArgs: [usuario.id]);
  }
}

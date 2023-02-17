import 'package:clicker/pages/pantallaEstadisticas.dart';
import 'package:clicker/pages/pantallaLogin.dart';
import 'package:clicker/pages/pantallaMejoras.dart';
import 'package:clicker/pages/pantallaOpciones.dart';
import 'package:clicker/pages/pantallaRegistro.dart';
import 'package:clicker/pages/pantallaPrincipal.dart';
import 'package:flutter/material.dart';
import 'globals.dart' as globals;

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  globals.handler.initializedDB();
  runApp(const Clicker());
}

class Clicker extends StatelessWidget {
  const Clicker({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Clicker',
      initialRoute: "/pantallaLogin",
      routes: {
        "/pantallaLogin": (BuildContext context) => PantallaLogin(),
        "/pantallaRegistro": (BuildContext context) => PantallaRegistro(),
        "/pantallaPrincipal": (BuildContext context) => PantallaPrincipal(),
        "/pantallaMejoras": (BuildContext context) => PantallaMejoras(),
        "/pantallaEstadisticas": (BuildContext context) => PantallaEstadisticas(),
        "/pantallaOpciones": (BuildContext context) => PantallaOpciones(),
      },
    );
  }
}

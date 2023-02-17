import 'dart:async';
import 'dart:math';
import 'package:flutter/material.dart';
import '../globals.dart' as globals;
import 'package:audioplayers/audioplayers.dart';

class PantallaPrincipal extends StatefulWidget {
  const PantallaPrincipal({super.key});

  @override
  State<PantallaPrincipal> createState() => _State();
}

class _State extends State<PantallaPrincipal> {
  late Timer _timer;
  int tiempoApacicionDiamante = 0;
  int duracionBonificacion = 0;
  int randomNum = 0;

  double randomMarginTop = 0.0;
  double randomMarginLeft = 0.0;

  bool visibilidadDiamante = false;
  String rutaBackgroundBonificacion =
      "assets/images/background_principal_2.png";

  int generarRandomNum(int min, int max) {
    return Random().nextInt(max - min + 1) + min;
  }

  double generarPosicionAleatoria(double min, double max) {
    return min + (max - min) * Random().nextDouble();
  }

  @override
  void initState() {
    randomNum = generarRandomNum(5, 10);
    randomMarginTop = generarPosicionAleatoria(0, 0.9);
    randomMarginLeft = generarPosicionAleatoria(0, 0.9);
    temporizador();
    super.initState();
  }

  void temporizador() {
    const oneSec = Duration(seconds: 1);
    _timer = Timer.periodic(
      oneSec,
      (Timer timer) async {
        await globals.insertarTodo();
        setState(() {
          globals.usuario.dinero;
          tiempoApacicionDiamante++;

          if (tiempoApacicionDiamante == randomNum) {
            visibilidadDiamante = true;
          }

          if (duracionBonificacion > 0) {
            duracionBonificacion--;
            if (duracionBonificacion == 0) {
              globals.pararMusicaDiamante();
              tiempoApacicionDiamante = 0;
              rutaBackgroundBonificacion =
                  "assets/images/background_principal_2.png";
              globals.multiplicadorEspecial = 1;
              randomNum = generarRandomNum(5, 10);
              randomMarginTop = generarPosicionAleatoria(0.1, 0.9);
              randomMarginLeft = generarPosicionAleatoria(0.1, 0.9);
            }
          }
        });
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Stack(
        children: [
          Container(
            width: globals.logicalWidth * 1,
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage(rutaBackgroundBonificacion),
                fit: BoxFit.cover,
              ),
            ),
            child: Column(
              children: [
                Expanded(
                  flex: 2,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Container(
                        width: globals.logicalWidth * 0.9,
                        color: Colors.grey[400]?.withOpacity(0.5),
                        child: Text(
                          globals.usuario.username,
                          style: TextStyle(
                            fontFamily: "Minecraft",
                            fontSize: globals.pixelRatio * 10,
                            fontWeight: FontWeight.bold,
                            color: Colors.brown[800],
                          ),
                          textAlign: TextAlign.center,
                        ),
                      ),
                      Padding(padding: EdgeInsets.all(globals.pixelRatio * 1)),
                      Container(
                        width: globals.logicalWidth * 0.8,
                        height: globals.pixelRatio * 14,
                        color: Colors.grey[400]?.withOpacity(0.5),
                        child: Text(
                          globals.usuario.dinero.toString(),
                          textAlign: TextAlign.center,
                          style: TextStyle(
                              fontFamily: "Minecraft",
                              fontSize: globals.pixelRatio * 12,
                              fontWeight: FontWeight.bold,
                              color: Colors.brown[800]),
                        ),
                      ),
                    ],
                  ),
                ),
                Expanded(
                  flex: 8,
                  child: Center(
                    child: IconButton(
                      onPressed: clickMena,
                      icon: Image.asset("assets/images/gold.webp"),
                      iconSize: globals.logicalWidth * 0.7,
                    ),
                  ),
                ),
              ],
            ),
          ),
          Visibility(
            visible: visibilidadDiamante,
            child: Container(
              margin: EdgeInsets.only(
                top: globals.logicalHeight * randomMarginTop,
                left: globals.logicalWidth * randomMarginLeft,
              ),
              child: IconButton(
                onPressed: clickDiamante,
                icon: Image.asset("assets/images/diamond.png"),
                iconSize: globals.logicalWidth * 0.4,
              ),
            ),
          )
        ],
      ),
    );
  }

  void clickMena() {
    if (globals.activarEfectosSonido) {
      globals.reproductorEfectoSonidoMena.setVolume(1.0);
      globals.reproducirEfectoSonidoMena("audio/monedas_1.mp3");
    }
    globals.usuario.clicksRealizados++;
    setState(() {
      globals.usuario.dinero += globals.rendimientoMejorasActivas();
    });
  }

  void clickDiamante() {
    if (globals.activarEfectosSonido) {
      globals.reproducirEfectoSonidoDiamante("audio/diamante.mp3");
      globals.reproducirMusicaDiamante("audio/especial.mp3");
    }

    setState(() {
      visibilidadDiamante = false;
      globals.multiplicadorEspecial = 2;
      rutaBackgroundBonificacion = "assets/images/background_diamonds.jpg";
      duracionBonificacion = 10;
      globals.usuario.diamantesTotales++;
    });
  }
}

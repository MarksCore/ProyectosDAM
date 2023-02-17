import 'dart:async';
import 'package:flutter/material.dart';
import '../globals.dart' as globals;

class PantallaEstadisticas extends StatefulWidget {
  @override
  State<PantallaEstadisticas> createState() => _State();
}

class _State extends State<PantallaEstadisticas> {
  late Timer _timer;

  @override
  void initState() {
    temporizador();
    super.initState();
  }

  void temporizador() {
    const oneSec = Duration(seconds: 0);
    _timer = Timer.periodic(
      oneSec,
      (Timer timer) {
        setState(() {
          // globals.usuario.clicksRealizados;
          // globals.usuario.menasConsumidas;
        });
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage("assets/images/background_estadisticas.jpg"),
                fit: BoxFit.cover,
              ),
            ),
            child: Column(
              children: [
                Expanded(
                  flex: 2,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Estadísticas",
                        style: TextStyle(
                            fontFamily: "Minecraft",
                            fontSize: globals.pixelRatio * 10,
                            fontWeight: FontWeight.bold,
                            color: Colors.yellow[600]),
                      ),
                    ],
                  ),
                ),
                Expanded(
                  flex: 8,
                  child: Container(
                    width: globals.logicalWidth * 0.95,
                    padding: EdgeInsets.only(bottom: globals.logicalHeight*0.05),
                    child: Card(
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(25)),
                      color: Colors.brown[900],
                      child: Container(
                        padding: EdgeInsets.only(top: globals.logicalHeight*0.05),
                        child: Row(
                          children: [
                            Padding(padding: EdgeInsets.only(left: globals.logicalWidth*0.05)),
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  "Clicks totales de mena: ${globals.usuario.clicksRealizados}",
                                  style: TextStyle(
                                      fontFamily: "Minecraft",
                                      fontSize: globals.pixelRatio * 7,
                                      color: Colors.white),
                                ),
                                Text(
                                  "Menas gastadas: ${globals.usuario.menasConsumidas}",
                                  style: TextStyle(
                                      fontFamily: "Minecraft",
                                      fontSize: globals.pixelRatio * 7,
                                      color: Colors.white),
                                ),
                                Text(
                                  "Diamantes obtenidos: ${globals.usuario.diamantesTotales}",
                                  style: TextStyle(
                                      fontFamily: "Minecraft",
                                      fontSize: globals.pixelRatio * 7,
                                      color: Colors.white),
                                ),
                                Text(
                                  "Menas por click: ${globals.rendimientoMejorasActivas()}",
                                  style: TextStyle(
                                      fontFamily: "Minecraft",
                                      fontSize: globals.pixelRatio * 7,
                                      color: Colors.white),
                                ),
                                Text(
                                  "Menas por segundo: ${globals.rendimientoMejorasPasivas()}",
                                  style: TextStyle(
                                      fontFamily: "Minecraft",
                                      fontSize: globals.pixelRatio * 7,
                                      color: Colors.white),
                                ),
                                Text(
                                  "Mejoras obtenidas: \n\t\t\t\t\t" + devolverNombresMejoras(),
                                  style: TextStyle(
                                      fontFamily: "Minecraft",
                                      fontSize: globals.pixelRatio * 7,
                                      color: Colors.white),
                                ),
                              ],
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }

  String devolverNombresMejoras() {
    String listaMejorasActivas = "";
    String listaMejorasPasivas = "";

    for (int i = 0; i < globals.usuario.mejorasActivas.length; i++) {
      listaMejorasActivas =
          listaMejorasActivas + globals.usuario.mejorasActivas[i].nombre + "\n\t\t\t\t\t";
    }

    for (int i = 0; i < globals.usuario.mejorasPasivas.length; i++) {
      listaMejorasPasivas =
          listaMejorasPasivas + globals.usuario.mejorasPasivas[i].nombre + " (x" + globals.usuario.mejorasPasivas[i].nivel.toString() + ")\n\t\t\t\t\t";
    }

    return listaMejorasActivas + listaMejorasPasivas;
  }
}

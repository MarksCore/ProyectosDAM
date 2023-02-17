import 'dart:async';
import 'package:clicker/objetos/MejorasPasivas.dart';
import 'package:flutter/material.dart';
import '../objetos/MejorasActivas.dart';
import '../globals.dart' as globals;

class PantallaMejoras extends StatefulWidget {
  @override
  State<PantallaMejoras> createState() => _State();
}

class _State extends State<PantallaMejoras> {
  late Timer _timer;
  Color cardColorPico = globals.pico.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
  Color cardColorPala = globals.pala.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
  Color cardColorAranha = globals.aranha.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
  Color cardColorMurcielago = globals.murcielago.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
  Color cardColorAldeano = globals.aldeano.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
  Color cardColorAllay = globals.allay.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
  Color cardColorPiglin = globals.piglin.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
  Color cardColorEnderman = globals.enderman.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;

  @override
  void initState() {
    temporizador();
    super.initState();
  }

  void temporizador() {
    const oneSec = Duration(seconds: 1);
    _timer = Timer.periodic(
      oneSec,
      (Timer timer) {
        setState(() {
          globals.usuario.dinero += globals.rendimientoMejorasPasivas();
        });
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage("assets/images/background_mejoras.png"),
            fit: BoxFit.cover,
          ),
        ),
        child: ListView(
          children: [
            // MEJORA PICO.
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorPico,
                child: InkWell(
                  onTap: () async {
                    await actualizarMejoraActiva(globals.pico);
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.pico.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.pico.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Nivel: ${globals.pico.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.pico.coste.toString(),
                                style: TextStyle(
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),

            // MEJORA PALA.
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorPala,
                child: InkWell(
                  onTap: () async {

                    await actualizarMejoraActiva(globals.pala);
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.pala.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.pala.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Nivel: ${globals.pala.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.pala.coste.toString(),
                                style: TextStyle(
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            // MEJORA ARAÑA.
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorAranha,
                child: InkWell(
                  onTap: () async {
                    await globals.actualizarMejoraPasiva(globals.aranha);
                    cambiarColorCarta();
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.aranha.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.aranha.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Cantidad: ${globals.aranha.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.aranha.coste.toString(),
                                style: TextStyle(
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            // MEJORA MURCIÉLAGO.
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorMurcielago,
                child: InkWell(
                  onTap: () async {

                      await globals.actualizarMejoraPasiva(globals.murcielago);
                      cambiarColorCarta();
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.murcielago.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.murcielago.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Cantidad: ${globals.murcielago.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.murcielago.coste.toString(),
                                style: TextStyle(
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            //MEJORA ALDEANO.
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorAldeano,
                child: InkWell(
                  onTap: () async {

                    await globals.actualizarMejoraPasiva(globals.aldeano);
                    cambiarColorCarta();
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.aldeano.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.aldeano.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Cantidad: ${globals.aldeano.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.aldeano.coste.toString(),
                                style: TextStyle(
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            // MEJORA ALLAY.
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorAllay,
                child: InkWell(
                  onTap: () async {

                    await globals.actualizarMejoraPasiva(globals.allay);
                    cambiarColorCarta();
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.allay.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.allay.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Cantidad: ${globals.allay.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.allay.coste.toString(),
                                style: TextStyle(
                                    color: Colors.black,
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            // MEJORA PIGLIN
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorPiglin,
                child: InkWell(
                  onTap: () async {

                    await globals.actualizarMejoraPasiva(globals.piglin);
                    cambiarColorCarta();
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.piglin.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.piglin.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Cantidad: ${globals.piglin.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.piglin.coste.toString(),
                                style: TextStyle(
                                    color: Colors.black,
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
            Container(
              height: globals.logicalHeight * 0.11,
              child: Card(
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15)),
                elevation: 7,
                color: cardColorEnderman,
                child: InkWell(
                  onTap: () async {
                      await globals.actualizarMejoraPasiva(globals.enderman);
                      cambiarColorCarta();
                  },
                  child: Row(
                    children: [
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Image.asset(
                            globals.enderman.ruta,
                            width: globals.logicalWidth * 0.15,
                          ),
                        ),
                      ),
                      Expanded(
                        flex: 6,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Text(
                              globals.enderman.nombre,
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 7),
                            ),
                            Text(
                              "Cantidad: ${globals.enderman.nivel}",
                              style: TextStyle(
                                  fontFamily: "Minecraft",
                                  fontSize: globals.pixelRatio * 6),
                            ),
                          ],
                        ),
                      ),
                      Expanded(
                        flex: 2,
                        child: Center(
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Text(
                                globals.enderman.coste.toString(),
                                style: TextStyle(
                                    color: Colors.black,
                                    fontFamily: "Minecraft",
                                    fontSize: globals.pixelRatio * 7),
                              ),
                              Image(
                                image: AssetImage("assets/images/gold.webp"),
                                width: globals.pixelRatio * 7,
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Future<void> actualizarMejoraActiva(MejorasActivas mejora) async {
    if (globals.usuario.dinero >= mejora.coste) {
      if (!globals.usuario.mejorasActivas.contains(mejora)) {
        globals.usuario.mejorasActivas.add(mejora);

        if (mejora.nombre.contains("Pico")) {
          cardColorPico = Colors.green;
        }
        if (mejora.nombre.contains("Pala")) {
          cardColorPala = Colors.green;
        }

      }
      mejora.nivel++;
      mejora.vecesUpgradeado++;
      globals.usuario.dinero -= mejora.coste;
      mejora.coste;
      globals.usuario.menasConsumidas += mejora.coste;
      mejora.multiplicar();
      modificarApariencia(mejora);
      MejorasActivas mejorasActivas = MejorasActivas(
          mejora.id,
          globals.usuario.id,
          mejora.nombre,
          mejora.coste,
          mejora.produccion,
          mejora.mC,
          mejora.mP,
          mejora.nivel,
          mejora.vecesUpgradeado,
          mejora.ruta);
      await globals.handler.insertUsuario(globals.usuario);
      await globals.handler.insertMejoraActiva(mejorasActivas);
    }
  }

  void modificarApariencia (MejorasActivas mejora) {
    setState(() {
      String pico_o_pala = mejora.nombre.substring(0, 4);
      if (mejora.vecesUpgradeado >= 6 && mejora.vecesUpgradeado < 11) {
        mejora.nombre = "$pico_o_pala de piedra";
        mejora.ruta =
        "assets/images/${pico_o_pala.toLowerCase()}_piedra.webp";
        if (mejora.vecesUpgradeado == 6) {
          mejora.nivel = 1;
        }
      } else if (mejora.vecesUpgradeado >= 11 &&
          mejora.vecesUpgradeado < 16) {
        mejora.nombre = "$pico_o_pala de hierro";
        mejora.ruta =
        "assets/images/${pico_o_pala.toLowerCase()}_hierro.webp";
        if (mejora.vecesUpgradeado == 11) {
          mejora.nivel = 1;
        }
      } else if (mejora.vecesUpgradeado >= 16 &&
          mejora.vecesUpgradeado < 21) {
        mejora.nombre = "$pico_o_pala de diamante";
        mejora.ruta =
        "assets/images/${pico_o_pala.toLowerCase()}_diamante.webp";
        if (mejora.vecesUpgradeado == 16) {
          mejora.nivel = 1;
        }
      } else if (mejora.vecesUpgradeado >= 21 &&
          mejora.vecesUpgradeado < 26) {
        mejora.nombre = "$pico_o_pala de netherita";
        mejora.ruta =
        "assets/images/${pico_o_pala.toLowerCase()}_netherita.webp";
        if (mejora.vecesUpgradeado == 21) {
          mejora.nivel = 1;
        }
      }
    });
  }

  void cambiarColorCarta () {
    setState(() {
      cardColorAranha = globals.aranha.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
      cardColorMurcielago = globals.murcielago.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
      cardColorAldeano = globals.aldeano.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
      cardColorAllay = globals.allay.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
      cardColorPiglin = globals.piglin.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
      cardColorEnderman = globals.enderman.nivel == 0 ? Colors.grey.withOpacity(0.6) : Colors.green;
    });
  }
}

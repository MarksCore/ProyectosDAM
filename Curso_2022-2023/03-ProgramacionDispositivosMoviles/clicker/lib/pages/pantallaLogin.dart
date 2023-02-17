import 'dart:async';

import 'package:audioplayers/audioplayers.dart';
import 'package:clicker/objetos/MejorasActivas.dart';
import 'package:clicker/objetos/MejorasPasivas.dart';
import 'package:clicker/objetos/Usuario.dart';
import 'package:clicker/pages/pantallaRegistro.dart';
import 'package:flutter/material.dart';

import '../globals.dart' as globals;
import '../barra/home.dart';

class PantallaLogin extends StatefulWidget {
  const PantallaLogin({super.key});

  @override
  State<PantallaLogin> createState() => _State();
}

class _State extends State<PantallaLogin> {
  late Timer _timer;
  String username = "";
  String password = "";
  String mensajeErrorUsuario = "";
  String mensajeErrorPassword = "";
  bool ocultarPassword = true;
  int tiempoErrorUsuario = -1;
  int tiempoErrorPassword = -1;

  @override
  initState() {
    globals.pararMusicaInicio();
    if (globals.activarMusica) {
      globals.reproducirMusica("audio/minecraft_main_ost.mp3");
      globals.reproductorMusica.setReleaseMode(ReleaseMode.loop);
    }
    temporizador();
    super.initState();
  }

  void temporizador() {
    const oneSec = Duration(seconds: 1);
    _timer = Timer.periodic(
      oneSec,
      (Timer timer) {
        setState(() {
          if (tiempoErrorUsuario >= 0) {
            tiempoErrorUsuario--;
          }
          if (tiempoErrorUsuario == 0) {
            mensajeErrorUsuario = "";
          }

          if (tiempoErrorPassword >= 0) {
            tiempoErrorPassword--;
          }
          if (tiempoErrorPassword == 0) {
            mensajeErrorPassword = "";
          }
        });
      },
    );
  }

  TextEditingController controllerUsername = TextEditingController();
  TextEditingController controllerPassword = TextEditingController();
  final formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GestureDetector(
        onTap: () {
          /*This method here will hide the soft keyboard.*/
          FocusScope.of(context).requestFocus(new FocusNode());
        },
        child: Container(
          decoration: BoxDecoration(
            image: DecorationImage(
              image: AssetImage("assets/images/background.gif"),
              fit: BoxFit.cover,
            ),
          ),
          child: Form(
            key: formKey,
            child: Column(
              children: [
                Expanded(
                  flex: 2,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Miner Clicker",
                        style: TextStyle(
                          fontFamily: "MinecraftTitle",
                          fontSize: globals.pixelRatio * 15,
                          color: Colors.white,
                        ),
                      ),
                    ],
                  ),
                ),
                Expanded(
                  flex: 8,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Login",
                        style: TextStyle(
                          fontFamily: "Minecraft",
                          color: Colors.white,
                          fontSize: globals.pixelRatio * 7,
                        ),
                      ),
                      Container(
                        width: globals.logicalWidth * 0.70,
                        child: TextFormField(
                          controller: controllerUsername,
                          style: TextStyle(
                              fontSize: globals.pixelRatio * 5,
                              color: Colors.blueGrey),
                          decoration: InputDecoration(
                            labelText: "Nombre de usuario",
                            filled: true,
                            fillColor: Colors.blueGrey[200],
                            labelStyle: TextStyle(color: Colors.white),
                            errorStyle: TextStyle(color: Colors.yellow),
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: new BorderSide(
                                  color: Colors.white,
                                  width: globals.pixelRatio * 1),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            errorBorder: OutlineInputBorder(
                              borderSide: new BorderSide(
                                  color: Colors.yellow,
                                  width: globals.pixelRatio * 1),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            focusedErrorBorder: new OutlineInputBorder(
                              borderSide: new BorderSide(
                                  color: Colors.yellow,
                                  width: globals.pixelRatio * 1),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            contentPadding: EdgeInsets.symmetric(
                                vertical: globals.logicalHeight * 0.02,
                                horizontal: globals.logicalHeight * 0.02),
                          ),
                          onSaved: (value) {
                            username = value!;
                          },
                          validator: (value) {
                            if (value!.isEmpty) {
                              return "Introduce un nombre de usuario.";
                            }
                          },
                        ),
                      ),
                      Text(
                        mensajeErrorUsuario,
                        style: TextStyle(
                          color: Colors.yellow,
                          fontWeight: FontWeight.bold,
                        ),
                        textAlign: TextAlign.center,
                      ),
                      Padding(padding: EdgeInsets.all(5)),
                      Container(
                        width: globals.logicalWidth * 0.70,
                        child: TextFormField(
                          controller: controllerPassword,
                          obscureText: ocultarPassword,
                          style: TextStyle(
                            fontSize: globals.pixelRatio * 5,
                            color: Colors.blueGrey,
                          ),
                          decoration: InputDecoration(
                            labelText: "Contraseña",
                            filled: true,
                            fillColor: Colors.blueGrey[200],
                            labelStyle: TextStyle(color: Colors.white),
                            errorStyle: TextStyle(color: Colors.yellow),
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(20),
                            ),
                            focusedBorder: OutlineInputBorder(
                              borderSide: new BorderSide(
                                  color: Colors.white,
                                  width: globals.pixelRatio * 1),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            errorBorder: OutlineInputBorder(
                              borderSide: new BorderSide(
                                  color: Colors.yellow,
                                  width: globals.pixelRatio * 1),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            focusedErrorBorder: new OutlineInputBorder(
                              borderSide: new BorderSide(
                                  color: Colors.yellow,
                                  width: globals.pixelRatio * 1),
                              borderRadius: BorderRadius.circular(20),
                            ),
                            contentPadding: EdgeInsets.symmetric(
                              vertical: globals.logicalHeight * 0.02,
                              horizontal: globals.logicalHeight * 0.02,
                            ),
                            suffixIcon: GestureDetector(
                              onTap: () {
                                cambiarVisibilidadPassword();
                              },
                              child: Icon(
                                ocultarPassword
                                    ? Icons.visibility
                                    : Icons.visibility_off,
                                color: Colors.blueGrey[900],
                              ),
                            ),
                          ),
                          onSaved: (value) {
                            password = value!;
                          },
                          validator: (value) {
                            if (value!.isEmpty) {
                              return "Introduce una contraseña.";
                            }
                          },
                        ),
                      ),
                      Text(
                        mensajeErrorPassword,
                        style: TextStyle(
                          color: Colors.yellow,
                          fontWeight: FontWeight.bold,
                        ),
                        textAlign: TextAlign.center,
                      ),
                      Padding(padding: EdgeInsets.all(5)),
                      Container(
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: [
                            OutlinedButton(
                              onPressed: () {
                                Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => PantallaRegistro()),
                                );
                              },
                              style: ButtonStyle(
                                  backgroundColor: MaterialStateProperty.all(
                                      Colors.blueGrey[900]),
                                  foregroundColor:
                                      MaterialStateProperty.all(Colors.white)),
                              child: Text("Registrarse"),
                            ),
                            OutlinedButton(
                                onPressed: () async {
                                  loguearUsuario(context);
                                },
                                style: ButtonStyle(
                                    backgroundColor: MaterialStateProperty.all(
                                        Colors.blueGrey[900]),
                                    foregroundColor: MaterialStateProperty.all(
                                        Colors.white)),
                                child: Text("Entrar")),
                          ],
                        ),
                      ),
                      Padding(
                        padding: EdgeInsets.only(
                          bottom: globals.logicalHeight * 0.1,
                        ),
                      )
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void cambiarVisibilidadPassword() {
    setState(() {
      ocultarPassword = !ocultarPassword;
    });
  }

  Future<bool> comprobarExistenciaUsuarioBD() async {
    var usuarios = await globals.handler.retrieveUsuario();

    for (Usuario usuario in usuarios!) {
      if (usuario.username == controllerUsername.text) {
        mensajeErrorUsuario = "";
        return true;
      }
    }
    tiempoErrorUsuario = 3;
    setState(() {
      mensajeErrorUsuario = "El usuario no existe.";
    });
    return false;
  }

  Future<bool> comprobarContrasenaUsuarioBD() async {
    var usuario = await globals.handler
        .retrieveUsuarioEspecifico(controllerUsername.text);

    if (usuario?.elementAt(0).password == controllerPassword.text) {
      mensajeErrorPassword = "";
      return true;
    }
    tiempoErrorPassword = 3;
    setState(() {
      mensajeErrorPassword = "La contraseña es incorrecta.";
    });
    return false;
  }

  // Extraer usuario de BD, cargar sus datos de partida
  asignarUsuario() async {
    // Lista con 1 usuario.
    var listaUsuario = await globals.handler
        .retrieveUsuarioEspecifico(controllerUsername.text);

    // Cogemos usuario único de la lista (? significa que puede ser nulo).
    var user = listaUsuario?.elementAt(0);

    // Cargamos datos usuario de BD en el objeto usuario en eljuego.
    globals.usuario.username = stringCon(user?.username);
    globals.usuario.password = stringCon(user?.password);
    globals.usuario.id = intCon(user?.id);
    globals.usuario.dinero = intCon(user?.dinero);
    globals.usuario.clicksRealizados = intCon(user?.clicksRealizados);
    globals.usuario.menasConsumidas = intCon(user?.menasConsumidas);
    globals.usuario.diamantesTotales = intCon(user?.diamantesTotales);
    globals.usuario.menasClick = intCon(user?.menasClick);
    globals.usuario.menasSegundo = intCon(user?.menasSegundo);

    var mActivas = [MejorasActivas(1, 0, 'a', 0, 0, 0, 0, 0, 0, '')];
    var mPasivas = [MejorasPasivas(1, 0, 'a', 0, 0, 0, 0, 0, 0, '')];

    // En el caso de que las listas de mejoras sean null, mete las listas vacías anteriores (??).
    var mAct =
        await globals.handler.retrieveMejorasActivas(globals.usuario.id) ??
            mActivas;
    var mPas =
        await globals.handler.retrieveMejorasPasivas(globals.usuario.id) ??
            mPasivas;
    globals.usuario.mejorasActivas = mAct;
    globals.usuario.mejorasPasivas = mPas;
    cargarMejoras();
  }

  // Llamamos esta función para entrar en el juego.
  loguearUsuario(BuildContext context) async {
    // Comprobamos que exista usuario en BD y que la contraseña se corresponda con la del usuario en la BD.
    if (formKey.currentState!.validate()) {
      if (await comprobarExistenciaUsuarioBD()) {
        if (await comprobarContrasenaUsuarioBD()) {
          //
          await asignarUsuario();

          formKey.currentState!.save();

          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => Home()),
          );
        }
      }
    }
  }

  // Engañar compilador.
  int intCon(int? num) {
    if (num == null) {
      return 0;
    }
    return num;
  }

// Engañar compilador.
  String stringCon(String? cadena) {
    if (cadena == null) {
      return '';
    }
    return cadena;
  }

  void cargarMejoras() {
    for (MejorasActivas mejorasActivas in globals.usuario.mejorasActivas) {
      if (mejorasActivas.id == 1) {
        globals.pico = mejorasActivas;
      }
      if (mejorasActivas.id == 2) {
        globals.pala = mejorasActivas;
      }
    }
    for (MejorasPasivas mejorasPasivas in globals.usuario.mejorasPasivas) {
      if (mejorasPasivas.id == 1) {
        globals.aranha = mejorasPasivas;
      }
      if (mejorasPasivas.id == 2) {
        globals.murcielago = mejorasPasivas;
      }
      if (mejorasPasivas.id == 3) {
        globals.aldeano = mejorasPasivas;
      }
      if (mejorasPasivas.id == 4) {
        globals.allay = mejorasPasivas;
      }
      if (mejorasPasivas.id == 5) {
        globals.piglin = mejorasPasivas;
      }
      if (mejorasPasivas.id == 6) {
        globals.enderman = mejorasPasivas;
      }
    }
  }
}

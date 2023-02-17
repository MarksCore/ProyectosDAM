import 'dart:async';

import 'package:clicker/objetos/Usuario.dart';
import 'package:clicker/pages/pantallaLogin.dart';
import 'package:flutter/material.dart';

import '../globals.dart' as globals;

class PantallaRegistro extends StatefulWidget {
  const PantallaRegistro({super.key});

  @override
  State<PantallaRegistro> createState() => _State();
}

class _State extends State<PantallaRegistro> {
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
              image: AssetImage("assets/images/background_registro.gif"),
              fit: BoxFit.cover,
            ),
          ),
          child: Form(
            key: formKey,
            child: Column(
              children: [
                Expanded(
                  flex: 2,
                  child: Column(
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
                        "Registro",
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
                          color: Colors.yellow[100],
                          fontSize: globals.pixelRatio * 4,
                        ),
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
                                horizontal: globals.logicalHeight * 0.02),
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
                                      builder: (context) => PantallaLogin()),
                                );
                              },
                              style: ButtonStyle(
                                backgroundColor: MaterialStateProperty.all(
                                  Colors.blueGrey[900],
                                ),
                                foregroundColor:
                                    MaterialStateProperty.all(Colors.white),
                              ),
                              child: Text("Cancelar"),
                            ),
                            OutlinedButton(
                                onPressed: () async {
                                  registrarUsuario(context);
                                },
                                style: ButtonStyle(
                                    backgroundColor: MaterialStateProperty.all(
                                        Colors.blueGrey[900]),
                                    foregroundColor: MaterialStateProperty.all(
                                        Colors.white)),
                                child: Text("Registrar")),
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
        tiempoErrorUsuario = 3;
        setState(() {
          mensajeErrorUsuario = "El usuario ya existe.";
        });
        return false;
      }
    }
    setState(() {
      mensajeErrorUsuario = "";
    });
    return true;
  }

  registroDB() async {
    await globals.handler.insertUsuarioRegistro(Usuario.p(
        controllerUsername.text, controllerPassword.text, 0, 0, 0, 0, 0, 0));
  }

  registrarUsuario(BuildContext context) async {
    if (formKey.currentState!.validate()) {
      if (await comprobarExistenciaUsuarioBD()) {
        await registroDB();

        formKey.currentState!.save();

        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => PantallaLogin()),
        );
      }
    }
  }
}

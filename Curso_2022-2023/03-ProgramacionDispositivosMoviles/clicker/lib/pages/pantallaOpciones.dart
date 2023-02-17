import 'package:clicker/pages/pantallaLogin.dart';
import 'package:flutter/material.dart';
import 'package:restart_app/restart_app.dart';
import '../globals.dart' as globals;

class PantallaOpciones extends StatefulWidget {
  const PantallaOpciones({super.key});

  @override
  State<PantallaOpciones> createState() => _State();
}

class _State extends State<PantallaOpciones> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Opciones'),
      ),
      body: Center(
        child: Column(
          children: [
            Container(
              decoration: BoxDecoration(
                  border: Border(bottom: BorderSide(color: Colors.grey))),
              margin: EdgeInsets.only(top: globals.logicalHeight * 0.03),
              padding: EdgeInsets.only(
                  left: globals.logicalHeight * 0.04,
                  bottom: globals.logicalHeight * 0.01),
              child: Row(
                children: [
                  Expanded(
                    flex: 8,
                    child: Text(
                      'Música de fondo',
                      style: TextStyle(fontSize: globals.pixelRatio * 7),
                    ),
                  ),
                  Expanded(
                    flex: 2,
                    child: Switch(
                      value: globals.activarMusica,
                      onChanged: (value) {
                        setState(() {
                          globals.activarMusica = value;
                          if (globals.activarMusica) {
                            globals.reproductorMusica.setVolume(1.0);
                          } else {
                            globals.reproductorMusica.setVolume(0.0);
                          }
                        });
                      },
                      activeTrackColor: Colors.lightGreenAccent,
                      activeColor: Colors.green,
                    ),
                  )
                ],
              ),
            ),
            Container(
              decoration: BoxDecoration(
                  border: Border(bottom: BorderSide(color: Colors.grey))),
              margin: EdgeInsets.only(
                  top: globals.logicalHeight * 0.03,
                  bottom: globals.logicalHeight * 0.03),
              padding: EdgeInsets.only(left: globals.logicalHeight * 0.04),
              child: Row(
                children: [
                  Expanded(
                    flex: 8,
                    child: Text(
                      'Efectos de sonido',
                      style: TextStyle(fontSize: globals.pixelRatio * 7),
                    ),
                  ),
                  Expanded(
                    flex: 2,
                    child: Switch(
                      value: globals.activarEfectosSonido,
                      onChanged: (value) {
                        setState(() {
                          globals.activarEfectosSonido = value;
                        });
                      },
                      activeTrackColor: Colors.lightGreenAccent,
                      activeColor: Colors.green,
                    ),
                  )
                ],
              ),
            ),
            Container(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  OutlinedButton(
                    onPressed: () {
                      Restart.restartApp();
                    },
                    style: ButtonStyle(
                        backgroundColor:
                            MaterialStateProperty.all(Colors.blueGrey[900]),
                        foregroundColor:
                            MaterialStateProperty.all(Colors.white)),
                    child: Text("Cerrar sesión"),
                  ),
                  OutlinedButton(
                    onPressed: () async {
                      await globals.handler.deleteUsuario(globals.usuario.id);
                      Restart.restartApp();
                    },
                    style: ButtonStyle(
                        backgroundColor:
                            MaterialStateProperty.all(Colors.blueGrey[900]),
                        foregroundColor:
                            MaterialStateProperty.all(Colors.white)),
                    child: Text("Borrar usuario"),
                  ),
                  OutlinedButton(
                      onPressed: () async {
                        await globals.handler.resetearPartida(globals.usuario);
                        Restart.restartApp();
                      },
                      style: ButtonStyle(
                          backgroundColor:
                          MaterialStateProperty.all(Colors.blueGrey[900]),
                          foregroundColor:
                          MaterialStateProperty.all(Colors.white)),
                      child: Text("Resetear")),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

library my.globals;

import 'dart:ui';
import 'package:audioplayers/audioplayers.dart';
import 'package:clicker/objetos/MejorasActivas.dart';
import 'package:clicker/objetos/Usuario.dart';
import 'objetos/MejorasPasivas.dart';
import 'bbdd/dbHandler.dart';

Usuario usuario = Usuario(
    1,
    'username',
    'password',
    0,
    [MejorasActivas(0, 0, 'nombre', 0, 0, 0, 0, 0, 0, "")],
    [MejorasPasivas(0, 0, 'nombre', 0, 0, 0, 0, 0, 0, "")],
    0,
    0,
    0,
    0,
    0);

MejorasActivas pico = MejorasActivas(1, 0, "Pico de madera", 5, 2, 1.5, 1.3, 0,
    0, "assets/images/pico_madera.webp");
MejorasActivas pala = MejorasActivas(2, 0, "Pala de madera", 7, 3, 1.5, 1.3, 0,
    0, "assets/images/pala_madera.webp");
MejorasPasivas aranha = MejorasPasivas(1, 0, "Araña extractora", 15, 5, 1.2,
    1.5, 0, 0, "assets/images/spider.webp");
MejorasPasivas murcielago = MejorasPasivas(2, 0, "Murciélago picador", 30, 7,
    1.2, 1.5, 0, 0, "assets/images/bat.webp");
MejorasPasivas aldeano = MejorasPasivas(3, 0, "Aldeano currante", 50, 10, 1.2,
    1.5, 0, 0, "assets/images/aldeano.webp");
MejorasPasivas allay = MejorasPasivas(4, 0, "Allay recolector", 100, 20, 1.2,
    1.5, 0, 0, "assets/images/allay.webp");
MejorasPasivas piglin = MejorasPasivas(5, 0, "Piglin ayudante", 250, 50, 1.2,
    1.3, 0, 0, "assets/images/piglin.webp");
MejorasPasivas enderman = MejorasPasivas(6, 0, "Enderman amistoso", 500, 200,
    1.2, 1.3, 0, 0, "assets/images/enderman.webp");

var pixelRatio = window.devicePixelRatio;
var logicalScreenSize = window.physicalSize / pixelRatio;
var logicalWidth = logicalScreenSize.width;
var logicalHeight = logicalScreenSize.height;

AudioPlayer reproductorMusica = AudioPlayer();
AudioPlayer reproductorEfectoSonidoMena = AudioPlayer();
AudioPlayer reproductorEfectoSonidoDiamante = AudioPlayer();
AudioPlayer reproductorMusicaDiamante = AudioPlayer();

bool activarMusica = true;
bool activarEfectosSonido = false;

DBHandler handler = DBHandler();

int multiplicadorEspecial = 1;

int rendimientoMejorasActivas() {
  int suma = 0;

  if (usuario.mejorasActivas.contains(pico) ||
      usuario.mejorasActivas.contains(pala)) {
    for (int i = 0; i < usuario.mejorasActivas.length; i++) {
      suma += usuario.mejorasActivas[i].produccion;
    }
  } else {
    suma += 1;
  }

  return suma;
}

int rendimientoMejorasPasivas() {
  int suma = 0;

  for (int i = 0; i < usuario.mejorasPasivas.length; i++) {
    suma = suma + usuario.mejorasPasivas[i].produccion;
  }
  return suma * multiplicadorEspecial;
}

void reproducirMusica(String url) {
  reproductorMusica.play(AssetSource(url));
  reproductorMusica.setVolume(1.0);
}

void pararMusicaInicio() {
  reproductorMusica.stop();
}

void reproducirEfectoSonidoMena(String url) {
  reproductorEfectoSonidoMena.play(AssetSource(url));
}

void reproducirEfectoSonidoDiamante(String url) {
  reproductorEfectoSonidoDiamante.play(AssetSource(url));
  reproductorEfectoSonidoDiamante.setVolume(0.5);
}

void reproducirMusicaDiamante(String url) {
  reproductorMusicaDiamante.play(AssetSource(url));
  reproductorMusicaDiamante.setVolume(0.15);
}

void pararMusicaDiamante() {
  reproductorMusicaDiamante.stop();
}

// Función que recoge mejora y crea una nueva a partir del id del usuario y la añade a BD.
Future<void> actualizarMejoraPasiva(MejorasPasivas mejora) async {
  if (usuario.dinero >= mejora.coste) {
    if (!usuario.mejorasPasivas.contains(mejora)) {
      usuario.mejorasPasivas.add(mejora);
    }
    mejora.nivel++;
    mejora.vecesUpgradeado++;
    usuario.dinero -= mejora.coste;
    mejora.coste;
    usuario.menasConsumidas += mejora.coste;
    mejora.multiplicar();
    MejorasPasivas mejorasPasivas = MejorasPasivas(
        mejora.id,
        usuario.id,
        mejora.nombre,
        mejora.coste,
        mejora.produccion,
        mejora.mC,
        mejora.mP,
        mejora.nivel,
        mejora.vecesUpgradeado,
        mejora.ruta);
    await handler.insertUsuario(usuario);
    await handler.insertMejoraPasiva(mejorasPasivas);
  }
}

Future<void> insertarTodo() async {
  await handler.insertUsuario(usuario);
  for (MejorasActivas mejora in usuario.mejorasActivas) {
    await handler.insertMejoraActiva(mejora);
  }
  for (MejorasPasivas mejora in usuario.mejorasPasivas) {
    await handler.insertMejoraPasiva(mejora);
  }
}

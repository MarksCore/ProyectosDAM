import 'package:clicker/pages/pantallaEstadisticas.dart';
import 'package:clicker/pages/pantallaMejoras.dart';
import 'package:clicker/pages/pantallaOpciones.dart';
import 'package:clicker/pages/pantallaPrincipal.dart';
import 'package:flutter/material.dart';
import 'persistent_bottom_bar_scaffold.dart';


class Home extends StatelessWidget {

  @override
  Widget build(BuildContext context) {

    return PersistentBottomBarScaffold(
      items: [
        PersistentTabItem(
          tab: PantallaPrincipal(),
        ),
        PersistentTabItem(
          tab: PantallaMejoras(),
        ),
        PersistentTabItem(
          tab: PantallaEstadisticas(),
        ),
        PersistentTabItem(
          tab: PantallaOpciones(),
        ),
      ],
    );
  }
}
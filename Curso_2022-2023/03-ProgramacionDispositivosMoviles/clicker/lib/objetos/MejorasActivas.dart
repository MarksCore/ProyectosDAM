class MejorasActivas {
  int idUsuario;
  int id;
  String nombre;
  int coste;
  int produccion;
  double mP;
  double mC;
  int nivel;
  int vecesUpgradeado;
  String ruta;

  void multiplicar() {
    double p = this.produccion.toDouble() * this.mP;
    double c = this.coste.toDouble() * this.mC;
    this.produccion = p.toInt();
    this.coste = c.toInt();
  }

  MejorasActivas(this.id, this.idUsuario, this.nombre, this.coste, this.produccion, this.mP,
      this.mC, this.nivel, this.vecesUpgradeado, this.ruta);

  // Función que retorna un mapa a partir de la mejora.
  Map<String, dynamic> toMap() {
    return {
      'idUsuario': idUsuario,
      'id': id,
      'nombre': nombre,
      'coste': coste,
      'produccion': produccion,
      'mP': mP,
      'mC': mC,
      'nivel': nivel,
      'vecesUpgradeado': vecesUpgradeado,
      'ruta': ruta
    };
  }

  // Función que convierte el mapa a mejora.
  MejorasActivas.fromMap(Map<String, dynamic> result)
      : idUsuario = result["idUsuario"],
        id = result["id"],
        nombre = result["nombre"],
        coste = result["coste"],
        produccion = result["produccion"],
        mP = result["mP"],
        mC = result["mC"],
        nivel = result["nivel"],
        vecesUpgradeado = result["vecesUpgradeado"],
        ruta = result["ruta"];
}

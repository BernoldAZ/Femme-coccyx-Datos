//
// Created by usuario on 18/11/2018.
//

#include "JavaConection.h"

std::string JavaConection::readFile() {
    std::ifstream ficheroEntrada;
    string frase;

    ficheroEntrada.open ("C:/Users/usuario/Desktop//TEC/Estructuras de datos/proyecto 2/Femme-coccyx Datos/SendToC.txt");
    getline(ficheroEntrada, frase);
    ficheroEntrada.close();

    while (frase[frase.length()-1] != '}'){
        frase.pop_back();
    }
    frase = frase + ']' + '}';

    //std::cout << frase << std::endl;

    return frase;
}

void JavaConection::sendFile(string pJson) {
    std::ofstream myfile;
    myfile.open ("C:/Users/usuario/Desktop//TEC/Estructuras de datos/proyecto 2/Femme-coccyx Datos/SendToJava.txt");
    myfile << pJson;
    myfile.close();
}

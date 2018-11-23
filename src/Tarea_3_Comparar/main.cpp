#include <iostream>
#include <string>
#include <sstream>
#include <arbolavl.h>
#include "monticulo.h"
#include "conectar.h"
#include <fstream>
#include "SampleImage.h"
#include "JavaConection.h"
#include "JsonParser.h"

using namespace std;

int main()
{

    //arbol->getInOrder(true);
    //std::cout << "Ahora el monticulo" << std::endl;


    //tarea->print();

    //tarea->buscar(1)->getValue();



    JavaConection* conection = new JavaConection();
    string frase = conection->readFile();

    //conection->sendFile("hola como esta");
    std::cout << frase << std::endl;

    JavaConection *jc = new JavaConection();
    string m = jc->readFile();
    JsonParser *jp = new JsonParser(m);




    return 0;

}

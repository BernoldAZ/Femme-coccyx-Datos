#include <iostream>
#include <string>
#include <sstream>
#include <arbolavl.h>
#include "monticulo.h"
#include "conectar.h"
#include <fstream>
#include "SampleImage.h"
#include "JavaConection.h"

using namespace std;

int main()
{
    ArbolAVL *arbol = new ArbolAVL();

    SampleImage* image = new SampleImage(5,5,3,2,1,4);

    arbol->insert(image, 30);
    arbol->insert(image, 45);
    arbol->insert(image, 701);
    arbol->insert(image, 650);
    arbol->insert(image, 345);
    arbol->insert(image, 120);
    arbol->insert(image, 8);
    arbol->insert(image, 60);
    arbol->insert(image, 22);
    arbol->insert(image, 420);
    arbol->insert(image, 99);
    arbol->insert(image, 37);
    arbol->insert(image, 111);
    arbol->insert(image, 488);
    arbol->insert(image, 35);

    //arbol->getInOrder(true);
    //std::cout << "Ahora el monticulo" << std::endl;

    Monticulo *tarea = new Monticulo();

    tarea->insert(image,1);
    tarea->insert(image,2);
    tarea->insert(image,3);
    tarea->insert(image,4);
    tarea->insert(image,5);
    tarea->insert(image,6);
    tarea->insert(image,7);
    tarea->insert(image,8);
    tarea->insert(image,9);
    tarea->insert(image,10);
    tarea->insert(image,11);
    tarea->insert(image,12);

    //tarea->print();

    //tarea->buscar(1)->getValue();



    JavaConection* conection = new JavaConection();
    string frase = conection->readFile();

    //conection->sendFile("hola como esta");
    //std::cout << frase << std::endl;

    return 0;

}

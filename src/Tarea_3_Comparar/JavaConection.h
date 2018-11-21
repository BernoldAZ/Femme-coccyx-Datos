//
// Created by usuario on 18/11/2018.
//

#ifndef TAREA_3_COMPARAR_JAVACONECTION_H
#define TAREA_3_COMPARAR_JAVACONECTION_H
#include <string>
#include <fstream>
#include <iostream>
using namespace std;
class JavaConection {

public:
    string readFile();
    void sendFile(string pJson);

};


#endif //TAREA_3_COMPARAR_JAVACONECTION_H

//
// Created by Esteban Madrigal on 11/23/2018.
//

#include "JsonParser.h"
#include "SampleImage.h"
#include <json.hpp>
#include "main.cpp"
#include <fstream>
#include <iostream>
#include <list>

// for convenience
using json = nlohmann::json;

JsonParser::JsonParser(std::string content){

    arbol = new ArbolAVL();
    //montiuclo = new Monticulo();

    json jsonObject = json::parse(content);
    json list = jsonObject["list"];
    for(int sample = 0; sample<list.size(); sample++){

        int id = list[sample]["ID"];
        int posX = list[sample]["PosX"];
        int posY = list[sample]["PosY"];
        int block = list[sample]["Block"];
        int rgb = list[sample]["RGB"];
        int repetitions = list[sample]["Repetitions"];

        SampleImage *newSample = new SampleImage(id,posX,posY,block,rgb,repetitions);
        arbol->insert(newSample, newSample->getID());
        //montiuclo->insert(newSample,newSample->getID());
    }

};


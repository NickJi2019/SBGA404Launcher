//
//  test.cpp
//  test
//
//  Created by Nick 嵇 on 2023/1/18.
//

#include "test.hpp"
#include <iostream>

const char * jnaTest(){
    std::cout<<"ok"<<std::endl;
    return (new std::string("hello world"))->c_str();
}

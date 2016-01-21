/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.awt.Color;
import plugin.IPruningAlgoritmus;

/**
 *
 * @author Honza
 */
public class AlgorithmModel
{

    public final IPruningAlgoritmus algorithm;
    public Color color;

    public AlgorithmModel(IPruningAlgoritmus alg, Color c)
    {
        this.algorithm = alg;
        this.color = c;
    }
}

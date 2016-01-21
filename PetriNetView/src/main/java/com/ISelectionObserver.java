/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author Honza
 */
public interface ISelectionObserver
{

    void onSelection(Object source, ISelectionArgs args);

    void onUnSelecting(Object source, ISelectionArgs args);

}

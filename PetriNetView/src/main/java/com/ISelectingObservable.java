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
public interface ISelectingObservable
{

    void addSelectingListener(ISelectionObserver selectionObserver);

    void removeSelectingListener(ISelectionObserver selectionObserver);
}

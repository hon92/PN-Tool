/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin;

import java.util.List;
import net.NetSimulator;
import net.Transition;

/**
 *
 * @author Honza
 */
public interface IPruningAlgoritmus
{

    List<Transition> getAmpleSet(NetSimulator simulator);

}

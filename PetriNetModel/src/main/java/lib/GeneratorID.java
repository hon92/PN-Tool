package lib;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Honza
 */
public final class GeneratorID
{

    private static GeneratorID generator;
    private int id = 0;

    private GeneratorID()
    {
    }

    public static GeneratorID getInstance()
    {
        if (generator == null)
        {
            generator = new GeneratorID();
        }
        return generator;
    }

    public int getNewId()
    {
        return id++;
    }

    public void scaleId(int v)
    {
        if (v >= id)
        {
            this.id = v + 1;
        }
    }

    public void reset()
    {
        id = 0;
    }
}

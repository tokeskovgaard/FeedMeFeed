package dk.tokebroedsted.commons.client.fragment.fragments;

import dk.tokebroedsted.commons.client.models.CalculationGWT;

/**
 * Created with IntelliJ IDEA.
 * User: toke
 * Date: 12-08-12
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public abstract class CalculationFragment<T> extends Fragment<T> {

    public abstract CalculationGWT getCalculationGWT();

}

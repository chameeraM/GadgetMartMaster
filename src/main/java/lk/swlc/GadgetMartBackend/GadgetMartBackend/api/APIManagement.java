
package lk.swlc.GadgetMartBackend.GadgetMartBackend.api;

import java.util.List;

public interface APIManagement<T, V,X, ID> {


    public List<X> getalldataAPI();

    public T searchspringAPI(ID id);

    public X searchspringnodeAPI(ID id);

    public T searchnodeAPI(ID id);

    public boolean deleteSpringAPI(ID id);

    public boolean deleteNodeAPI(ID id);

    public T postAPI(T t);

    public T updateAPI(ID id,T t);
}

package repositories;

import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Rafael Santos on 08-09-2017.
 */
public abstract class BaseRepository<T, K>{

    private static final String PERSISTENCE_UNIT_NAME = "uniquiz.application-PU";
    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    protected final Class<T> entityClass;

    public BaseRepository(){
        final ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    private EntityManager entityManager() {
        return factory.createEntityManager();
    }

    public T save(T entity){
        EntityManager em = entityManager();
        em.getTransaction().begin();
        T result = em.merge(entity);
        em.getTransaction().commit();
        em.close();

        return result;
    }

    public Optional<T> read(K id) {
        return Optional.ofNullable(this.entityManager().find(this.entityClass, id));
    }

    public Optional<T> findOne(K id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        return read(id);
    }

    protected TypedQuery<T> queryAll() {
        final String className = this.entityClass.getSimpleName();
        return entityManager().createQuery("SELECT e FROM " + className + " e ", this.entityClass);
    }

    private TypedQuery<T> query(String where) {

        final String className = this.entityClass.getSimpleName();
        return entityManager().createQuery("SELECT e FROM " + className + " e WHERE " + where, this.entityClass);
    }


    protected TypedQuery<T> query(String where, Map<String, Object> params) {
        assert params != null && params.size() > 0 : "Params must not be null or empty";

        final TypedQuery<T> q = query(where);
        params.entrySet().stream().forEach(e -> q.setParameter(e.getKey(), e.getValue()));
        return q;
    }

    public Iterable<T> findAll() {
        return match("1=1");
    }


    protected List<T> match(String where) {
        final TypedQuery<T> q = query(where);
        return q.getResultList();
    }

    protected List<T> match(String whereWithParameters, Map<String, Object> params) {
        assert params != null && params.size() > 0 : "Params must not be null or empty";

        final TypedQuery<T> q = query(whereWithParameters, params);
        return q.getResultList();
    }

    protected T matchOne(String where) {
        final TypedQuery<T> q = query(where);
        return q.getSingleResult();
    }

    protected T matchOne(String whereWithParameters, Map<String, Object> params) {
        final TypedQuery<T> q = query(whereWithParameters, params);
        return q.getSingleResult();
    }

    protected T matchOne(String where, Object... args) {
        final TypedQuery<T> q = query(where);
        boolean isArgName = true;
        String argName = "";
        for (final Object o : args) {
            if (isArgName) {
                argName = (String) o;
            } else {
                q.setParameter(argName, o);
            }
            isArgName = !isArgName;
        }
        return q.getSingleResult();
    }

}

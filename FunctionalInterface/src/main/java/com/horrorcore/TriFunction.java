package com.horrorcore;

@FunctionalInterface
public interface TriFunction<K, U, V, R> {
    R apply(K k, U u, V v);
    //R apply(K k, U u, V v, R r);
    default TriFunction<K, U, V, R> andThen(TriFunction<? super R, ? super U, ? super V, ? extends R> after) {
        return (k, u, v) -> after.apply(apply(k, u, v), u, v);
    }
}

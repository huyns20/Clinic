package com.example.clinic.mapper;

/** Implement explicitly for each feature; do not expose JPA entities in REST responses. */
public interface BaseMapper<E, Q, R> {
    E toEntity(Q request);
    R toResponse(E entity);
}

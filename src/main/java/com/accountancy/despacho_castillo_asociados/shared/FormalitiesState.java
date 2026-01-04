package com.accountancy.despacho_castillo_asociados.shared;

public enum FormalitiesState {

    PENDING(1),
    IN_PROGRESS(2),
    COMPLETED(3),
    REFUSED(4);

    private FormalitiesState(final int id) {
        this.id = id;
    }

    private final int id;

    public int getId() {
        return id;
    }

    public static FormalitiesState fromId(int id) {
        for (FormalitiesState state : FormalitiesState.values()) {
            if (state.getId() == id) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid FormalitiesState id: " + id);
    }

}

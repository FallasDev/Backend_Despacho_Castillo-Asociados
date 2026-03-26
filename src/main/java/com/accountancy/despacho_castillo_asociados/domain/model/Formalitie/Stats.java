package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import lombok.Getter;

@Getter
public class Stats {

        private Long total;
        private Long pending;
        private Long completed;

        public Stats(Long total, Long pending, Long completed) {
            this.total = total;
            this.pending = pending;
            this.completed = completed;
        }

}

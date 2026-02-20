package ee.lilian.veebipood.dto;

public record OrderRowDto(//Data transfer object ehk saan andmeid liigutada
        Long productId,
        int quantity
) {

}

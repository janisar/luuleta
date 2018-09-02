package ee.ardel.learningsession.models.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobReactRequest {

    private String id;
    private ReactionType reactionType;
}

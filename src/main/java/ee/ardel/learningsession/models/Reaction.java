package ee.ardel.learningsession.models;

import ee.ardel.learningsession.models.rest.ReactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter
@AllArgsConstructor
public class Reaction {

    private String userId;
    private ReactionType reactionType;
}

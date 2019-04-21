package ee.ardel.learningsession.models.rest;

import ee.ardel.learningsession.models.Location;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private String companyId;
    private Location location;
    private String description;
}

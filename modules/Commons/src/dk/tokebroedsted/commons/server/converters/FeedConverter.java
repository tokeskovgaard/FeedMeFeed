package dk.tokebroedsted.commons.server.converters;

import dk.tokebroedsted.commons.client.models.CalculationGWT;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.QuestionGWT;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.*;

import java.util.ArrayList;

public class FeedConverter implements Converter<FeedGWT, Feed> {

    @Override
    public Feed toServerObject(FeedGWT feedGWT) {
        Feed feed;

        if (feedGWT.getId() != null) {
            feed = new Feed(feedGWT.getId());
        } else {
            feed = new Feed();
        }
        User owner = ModelFactory.getUser("toke");

        feed.setOwner(owner);
        feed.setTitle(feedGWT.getTitle());
        feed.setCss(feedGWT.getCss());
        feed.setHtml(feedGWT.getHtml());

        InputConverter inputConverter = InputConverter.toServer(feed);

        ArrayList<FeedInput> feedInputs = new ArrayList<FeedInput>();
        for (InputGWT inputGWT : feedGWT.getInputs()) {
            feedInputs.add(inputConverter.toServerObject(inputGWT));
        }
        feed.setFeedInputs(feedInputs);

        return feed;
    }


    @Override
    public FeedGWT toGwtObject(Feed feed) {

        InputConverter inputConverter = InputConverter.toGwt();

        ArrayList<InputGWT> inputs = new ArrayList<InputGWT>();
        for (FeedInput input : feed.getFeedInputs()) {
            inputs.add(inputConverter.toGwtObject(input));
        }

        ArrayList<QuestionGWT> questionGWTs = new ArrayList<QuestionGWT>();
        for (Question question : feed.getQuestions()) {

            QuestionGWT.Type type = QuestionGWT.Type.valueOf(question.getType().name());
            if (type == null) {
                throw new RuntimeException("Encountered unexpected Type");
            }
            QuestionGWT questionGWT = new QuestionGWT(question.getId(), question.getFeed().getId(), question.getName(), type);
            questionGWTs.add(questionGWT);
        }

        ArrayList<CalculationGWT> calculationGWTs = new ArrayList<CalculationGWT>();
        for (Calculation calculation : feed.getCalculations()) {
            CalculationGWT calculationGWT = new CalculationGWT(calculation.getId(), calculation.getName(), calculation.getCalculation(), CalculationGWT.Type.bool);
            calculationGWTs.add(calculationGWT);
        }

        FeedGWT feedGWT = new FeedGWT();
        feedGWT.setTitle(feed.getTitle());
        feedGWT.setCss(feed.getCss() == null ? "" : feed.getCss());
        feedGWT.setHtml(feed.getHtml() == null ? "" : feed.getHtml());
        feedGWT.setFeedId(feed.getId());
        feedGWT.setInputs(inputs);
        feedGWT.setQuestions(questionGWTs);
        feedGWT.setCalculations(calculationGWTs);
        feedGWT.setOwner(feed.getOwner().getUsername());

        return feedGWT;
    }
}

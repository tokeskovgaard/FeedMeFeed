package dk.tokebroedsted.administration.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dk.tokebroedsted.administration.client.AdministrationService;
import dk.tokebroedsted.commons.client.models.CalculationGWT;
import dk.tokebroedsted.commons.client.models.FeedGWT;
import dk.tokebroedsted.commons.client.models.InputGWT;
import dk.tokebroedsted.commons.client.models.QuestionGWT;
import dk.tokebroedsted.commons.server.converters.FeedConverter;
import dk.tokebroedsted.hibernate.ModelFactory;
import dk.tokebroedsted.hibernate.tables.*;

import java.util.ArrayList;
import java.util.Set;

public class AdministrationServiceImpl extends RemoteServiceServlet implements AdministrationService {


    @Override
    public ArrayList<FeedGWT> getOwnedFeeds() {
        User user = ModelFactory.getUser("toke");
        Set<Feed> createdFeeds = user.getCreatedFeeds();

        ArrayList<FeedGWT> ownedFeeds = new ArrayList<FeedGWT>();
        FeedConverter feedConverter = new FeedConverter();
        for (Feed createdFeed : createdFeeds) {
            FeedGWT feedGWT = feedConverter.toGwtObject(createdFeed);
            ownedFeeds.add(feedGWT);
        }

        return ownedFeeds;
    }

    @Override
    public String saveFeed(FeedGWT feedGWT) {
        User owner = ModelFactory.getUser("toke");

        Feed feed = new Feed();

        feed.setOwner(owner);
        feed.setTitle(feedGWT.getTitle());
        feed.setCss(feedGWT.getCss());
        feed.setHtml(feedGWT.getHtml());
        ModelFactory.save(feed);

        for (InputGWT inputGWT : feedGWT.getInputs()) {
            createFeedInput(feed, inputGWT);
        }

        for (QuestionGWT questionGWT : feedGWT.getQuestions()) {
            createQuestion(feed, questionGWT);
        }

        for (CalculationGWT calculationGWT : feedGWT.getCalculations()) {
            createCalculation(feed, calculationGWT);
        }

        return "Yee";
    }

    private void createCalculation(Feed feed, CalculationGWT calculationGWT) {
        Calculation calculation = new Calculation(feed, calculationGWT.getName(), calculationGWT.getCalculation());
        ModelFactory.save(calculation);

    }

    private void createQuestion(Feed feed, QuestionGWT questionGWT) {
        Question.Type questionType = Question.Type.valueOf(questionGWT.getType().name());
        if (questionType == null) {
            throw new RuntimeException("Encountered an unexpected Type");
        }

        Question question = new Question(feed, questionType, questionGWT.getName());
        ModelFactory.save(question);
    }

    private void createFeedInput(Feed feed, InputGWT inputGWT) {
        FeedInput.Type inputType = FeedInput.Type.valueOf(inputGWT.getType().name());
        if (inputType == null) {
            throw new RuntimeException("Encountered an unexpected Type");
        }

        FeedInput feedInput = new FeedInput(feed, inputType, inputGWT.getName());

        ModelFactory.save(feedInput);
    }

}
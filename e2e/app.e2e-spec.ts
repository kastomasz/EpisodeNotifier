import { EpisodeNotifier2Page } from './app.po';

describe('episode-notifier-2 App', function() {
  let page: EpisodeNotifier2Page;

  beforeEach(() => {
    page = new EpisodeNotifier2Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});

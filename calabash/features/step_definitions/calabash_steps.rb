require 'calabash-android/calabash_steps'
Then (/^I select spinner by id "([^"]*)" and touch "([^"]*)"$/) do |spinnerid, my_text|
  spinner = query("android.widget.Spinner marked:'#{spinnerid}'")

  if spinner.empty?
    tap_when_element_exists("android.widget.Spinner * marked:'#{spinnerid}'")
  else
    touch(spinner)
  end


  touch("* {text CONTAINS '#{my_text}'}")
end

Then (/^I touch the view containing "([^"]*)"$/) do |my_text|
   touch("* {text CONTAINS 'my_text'}")
end

Then(/^I hide keyboard$/) do
   hide_soft_keyboard
end

